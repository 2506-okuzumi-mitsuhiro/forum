package com.example.forum.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String content;

    @Column
    private int content_id;

    @Column(name = "created_date", insertable = false, updatable = false)
    private Timestamp created_date;


    @Column(name = "updated_date", insertable = false, updatable = true)
    private Timestamp updated_date;
}
