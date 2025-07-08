package com.example.forum.service;

import com.example.forum.controller.form.ReportForm;
import com.example.forum.repository.ReportRepository;
import com.example.forum.repository.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    /*
     * レコード全件取得処理
     */
    public List<ReportForm> findAllReport() {
        List<Report> results = reportRepository.findAllByOrderByUpdatedDateDesc();
        List<ReportForm> reports = setReportForm(results);
        return reports;
    }

    /*
     * 指定条件レコード取得処理
     */
    public List<ReportForm> findNarrowDownReport(String start, String end) throws ParseException {
        Timestamp startDate = null;
        Timestamp endDate = null;
        Date dateStart = null;
        Date dateEnd = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (start.isBlank()){
            dateStart = format.parse("2020-01-01 00:00:00");
        }else {
            dateStart = format.parse(start + " 00:00:00");
        }

        startDate = new Timestamp(dateStart.getTime());

        if (end.isBlank()){
            dateEnd = new Timestamp(System.currentTimeMillis());
        }else {
            dateEnd = format.parse(end + " 23:59:59");
        }

        endDate = new Timestamp(dateEnd.getTime());

        List<Report> results = reportRepository.findAllByUpdatedDateBetweenOrderByUpdatedDateDesc(startDate, endDate);
        List<ReportForm> reports = setReportForm(results);
        return reports;
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<ReportForm> setReportForm(List<Report> results) {
        List<ReportForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ReportForm report = new ReportForm();
            Report result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            reports.add(report);
        }
        return reports;
    }

    /*
     * レコード追加、更新処理
     */
    public void saveReport(ReportForm reqReport) {
        Report saveReport = setReportEntity(reqReport);
        reportRepository.save(saveReport);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Report setReportEntity(ReportForm reqReport) {
        Report report = new Report();
        report.setId(reqReport.getId());
        report.setContent(reqReport.getContent());
        report.setUpdatedDate(reqReport.getUpdated_date());
        return report;
    }

    /*
     * レコード削除
     */
    public void deleteReport(Integer id) {
        reportRepository.deleteById(id);
    }

    /*
     * レコード取得処理
     */
    public ReportForm findReport(Integer id) {
        List<Report> results = new ArrayList<>();
        results.add((Report) reportRepository.findById(id).orElse(null));
        List<ReportForm> reports = setReportForm(results);
        return reports.get(0);
    }
}
