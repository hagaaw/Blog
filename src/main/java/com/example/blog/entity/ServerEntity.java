package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name="server")
public class ServerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "server", cascade = CascadeType.ALL)
    List<ArticleEntity> articles;

    @OneToMany(mappedBy = "server", cascade = CascadeType.ALL)
    List<ServerUserEntity> serverUser;
    public ServerEntity(){}

}
