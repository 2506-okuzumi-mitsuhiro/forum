package com.example.forum.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "report")
@Getter
@Setter
public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column
    private String content;

    @Column(name = "created_date", insertable = false, updatable = false)
    private Timestamp createdDate;

    @Column(name = "updated_date", insertable = false, updatable = true)
    private Timestamp updatedDate;
}
