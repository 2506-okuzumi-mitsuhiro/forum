package com.example.forum.mapper;

import com.example.forum.repository.entity.Report;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ReportMapper {
    List<Report> selectAll();

    List<Report> selectByUpdatedDate(Timestamp startDate, Timestamp endDate);

    Report selectById(Integer id);

    void insert(Report saveReport);

    void update(Report saveReport);

    void delete(Integer id);
}
