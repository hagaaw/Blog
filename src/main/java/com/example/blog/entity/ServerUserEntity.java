package com.example.blog.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="server_user")
public class ServerUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="server_id")
    private ServerEntity server;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private ServerRole role;

    public ServerUserEntity(ServerEntity server, UserEntity user, ServerRole role) {
        this.server = server;
        this.user = user;
        this.role = role;
    }

    public ServerUserEntity() {

    }
}
