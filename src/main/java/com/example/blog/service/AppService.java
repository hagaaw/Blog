package com.example.blog.service;


import com.example.blog.entity.*;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.ServerRepository;
import com.example.blog.repository.ServerUserRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppService {
    private final ServerUserRepository serverUserRepository;
    public ArticleRepository articleRepository;
    private UserRepository userRepository;
    private ServerService serverService;

    @Autowired
    public AppService(ArticleRepository articleRepository, UserRepository userRepository, ServerService serverService, ServerUserRepository serverUserRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.serverService = serverService;
        this.serverUserRepository = serverUserRepository;
    }


    public void saveArticle(ArticleEntity newArticle) {
        articleRepository.save(newArticle);
    }


    public UserEntity getCurrentUserEntity(){
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()-> new IllegalArgumentException("user not found"));
    }

    public void addArticle(String title, String content, Long serverId) {
        ServerUserEntity serverUser = serverUserRepository.getServerUserEntitiesByServerAndUser(serverService.getServerById(serverId),getCurrentUserEntity()).orElseThrow(()-> new IllegalArgumentException("not found"));
        if (serverUser.getRole() == ServerRole.ADMIN || serverUser.getRole() == ServerRole.CREATOR){
            var newArticle = new ArticleEntity(title, LocalDateTime.now(), content, serverUser.getServer());
            saveArticle(newArticle);
        }

    }
}
