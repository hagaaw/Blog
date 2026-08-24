package com.example.blog.service;


import com.example.blog.entity.ArticleEntity;
import com.example.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {
    @Autowired
    public ArticleRepository articleRepository;

    public void saveArticle(ArticleEntity newArticle) {
        articleRepository.save(newArticle);
    }

    public List<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }
}
