package com.example.forum.controller;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.controller.form.ReportForm;
import com.example.forum.service.CommentService;
import com.example.forum.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class ForumController {
    @Autowired
    ReportService reportService;

    @Autowired
    CommentService commentService;

    /*
     * 投稿内容表示処理
     */
    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得
        List<ReportForm> contentData = reportService.findAllReport();
        // コメントを全件取得
        List<CommentForm> commentData = commentService.findAllComment();
        // form用の空のentity作成
        CommentForm commentForm = new CommentForm();
        // 準備した空のFormを保管
        mav.addObject("formModel", commentForm);
        // 画面遷移先を指定
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
        mav.addObject("contents", contentData);
        // コメントデータオブジェクトを保管
        mav.addObject("comments", commentData);
        return mav;
    }

    /*
     * 新規投稿画面表示
     */
    @GetMapping("/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        ReportForm reportForm = new ReportForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", reportForm);
        return mav;
    }

    /*
     * 新規投稿処理
     */
    @PostMapping("/add")
    public ModelAndView addContent(@ModelAttribute("formModel") ReportForm reportForm){
        // 投稿をテーブルに格納
        reportService.saveReport(reportForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     *  投稿削除機能処理
     */
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable("id") Integer id){
        // 対象の投稿をテーブルから削除
        reportService.deleteReport(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 投稿編集画面表示
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editContent(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView();
        // 対象の投稿を取得
        ReportForm contentData = reportService.findReport(id);
        // 画面遷移先を指定
        mav.setViewName("/edit");
        // 投稿データオブジェクトを保管
        mav.addObject("formModel", contentData);
        return mav;
    }

    /*
     * 投稿編集処理
     */
    @PutMapping("/update/{id}")
    public ModelAndView updateContent(@ModelAttribute("formModel") ReportForm reportForm, @PathVariable("id") Integer id){
        // オブジェクトにID情報を付与
        reportForm.setId(id);
        // 投稿を更新
        reportService.saveReport(reportForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 返信処理
     */
    @PostMapping("/comment/{id}")
    public ModelAndView comment(@ModelAttribute("formModel") CommentForm commentForm,
                                @PathVariable("id") Integer id){
        // オブジェクトに返信対象のID情報を付与
        commentForm.setContent_id(id);
        // オブジェクトに現在時刻の情報を付与
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        commentForm.setUpdated_date(currentTime);
        // 返信をテーブルに格納
        commentService.saveComment(commentForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * コメント編集画面表示
     */
    @GetMapping("/edit_comment/{id}")
    public ModelAndView editCommentContent(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView();
        // 対象のコメントを取得
        CommentForm comment = commentService.findComment(id);
        // 画面遷移先を指定
        mav.setViewName("/edit_comment");
        // 投稿データオブジェクトを保管
        mav.addObject("formModel", comment);
        return mav;
    }

    /*
     * コメント編集処理
     */
    @PutMapping("/update_comment/{id}")
    public ModelAndView updateCommentContent(@ModelAttribute("formModel") CommentForm commentForm,
                                             @PathVariable("id") Integer id){
        // オブジェクトにID情報を付与
        commentForm.setId(id);
        // 更新時刻の情報を変更
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        commentForm.setUpdated_date(currentTime);
        // 投稿を更新
        commentService.saveComment(commentForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}
