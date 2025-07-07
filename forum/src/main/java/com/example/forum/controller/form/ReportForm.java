package com.example.forum.controller.form;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Getter
@Setter
public class ReportForm {
    private int id;

    @NotBlank(message="投稿内容を入力してください")
    private String content;

    private Timestamp created_date;
    private Timestamp updated_date;
}
