package com.example.blog.entity;


import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Entity
@Table(name="articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="date", nullable = false)
    private LocalDateTime dateTime;

    @Column(name="content")
    private String content;

    public ArticleEntity(){}

    public ArticleEntity(String title, LocalDateTime dateTime, String content) {
        this.title = title;
        this.dateTime = dateTime;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
