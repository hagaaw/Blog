package com.example.blog.controller;


import com.example.blog.entity.ArticleEntity;
import com.example.blog.entity.ServerEntity;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.ServerUserRepository;
import com.example.blog.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {
    private AppService appService;
    private ArticleRepository articleRepository;
    private ServerUserRepository serverUserRepository;

    @Autowired
    public AppController(AppService appService, ArticleRepository articleRepository,ServerUserRepository serverUserRepository) {
        this.appService = appService;
        this.articleRepository = articleRepository;
        this.serverUserRepository = serverUserRepository;
    }

    @GetMapping
    public String getApp(Model model){
        List<ArticleEntity> articleEntities = serverUserRepository.findByRoleAndUser(appService.getCurrentUserEntity()).orElseThrow().getServer().getArticles();
        model.addAttribute("articles", articleEntities);
        model.addAttribute("server",serverUserRepository.findByRoleAndUser(appService.getCurrentUserEntity()).orElseThrow().getServer());
        model.addAttribute("user",appService.getCurrentUserEntity());
        return "app-page";
    }

    @GetMapping("/add/article")
    public String getAddArticlePage(Model model
    ){
        model.addAttribute("server",serverUserRepository.findByRoleAndUser(appService.getCurrentUserEntity()).orElseThrow().getServer());
        return "add-article-page";
    }

    @PostMapping("/add/article")
    public String addArticle(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content", required = false) String content,
            @RequestParam(name = "serverId", required = false) Long serverId
            )
    {
        appService.addArticle(title, content, serverId);

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
