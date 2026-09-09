package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ServerUserEntity> serverUserEntities;


    public UserEntity(){}

    public UserEntity(String email,String login, String password,UserRole role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
