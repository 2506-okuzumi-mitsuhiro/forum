package com.example.forum.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReportForm {
    private int id;
    private String content;
    private Timestamp created_date;
    private Timestamp updated_date;
}
