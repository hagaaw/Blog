package com.example.blog.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name="server_id")
    private ServerEntity server;

    public ArticleEntity(){}

    public ArticleEntity(String title, LocalDateTime dateTime, String content, ServerEntity server) {
        this.title = title;
        this.dateTime = dateTime;
        this.content = content;
        this.server = server;
    }

}
