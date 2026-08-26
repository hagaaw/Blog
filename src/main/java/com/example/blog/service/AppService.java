package com.example.blog.service;


import com.example.blog.entity.ArticleEntity;
import com.example.blog.entity.UserEntity;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.UserRepository;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {
    public ArticleRepository articleRepository;
    private UserRepository userRepository;

    @Autowired
    public AppService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public void saveArticle(ArticleEntity newArticle) {
        articleRepository.save(newArticle);
    }

    public List<ArticleEntity> getAllArticlesCurrentUser() {
        return articleRepository.findByUserEntity(getCurrentUserEntity());
    }

    public UserEntity getCurrentUserEntity(){
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()-> new IllegalArgumentException("user not found"));
    }
}
