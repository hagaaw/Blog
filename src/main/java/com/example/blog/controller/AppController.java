package com.example.blog.controller;


import com.example.blog.entity.ArticleEntity;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {
    private AppService appService;
    private ArticleRepository articleRepository;

    @Autowired
    public AppController(AppService appService, ArticleRepository articleRepository) {
        this.appService = appService;
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public String getApp(Model model){
        List<ArticleEntity> articleEntities = appService.getAllArticles();
        model.addAttribute("articles", articleEntities);
        return "app-page";
    }

    @GetMapping("/add/article")
    public String getAddArticlePage(){
        return "add-article-page";
    }

    @PostMapping("/add/article")
    public String addArticle(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content", required = false) String content
            )
    {
        var newArticle = new ArticleEntity(title, LocalDateTime.now(),content);
        appService.saveArticle(newArticle);
        return "redirect:/app";
    }

    @PostMapping("/delete/article")
    public String deleteArticle(
            @RequestParam(name="id") Long id
    ){
        articleRepository.deleteById(id);
        return "redirect:/app";
    }

}
