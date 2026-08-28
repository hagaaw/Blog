package com.example.blog.controller;


import com.example.blog.entity.*;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.ServerUserRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserRepository userRepository;
    private AppService appService;
    private ArticleRepository articleRepository;
    private ServerUserRepository serverUserRepository;
    private final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    public AppController(AppService appService, ArticleRepository articleRepository, ServerUserRepository serverUserRepository, UserRepository userRepository) {
        this.appService = appService;
        this.articleRepository = articleRepository;
        this.serverUserRepository = serverUserRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getApp(Model model, @RequestParam(name="user", required = false)Long userId){
        if (userId == null){ userId = appService.getCurrentUserEntity().getId();}
        var user = appService.getCurrentUserEntity();
        logger.info(userId.toString());
        var creatorUser= userRepository.findAllById(userId).get();
        var server = serverUserRepository.findByRoleAndUser(creatorUser).orElseThrow().getServer();
        List<ArticleEntity> articleEntities = serverUserRepository.findByRoleAndUser(creatorUser).orElseThrow().getServer().getArticles();
        model.addAttribute("articles", articleEntities);
        model.addAttribute("server",server);
        model.addAttribute("user",user);
        var serverUser = serverUserRepository.getServerUserEntitiesByServerAndUser(serverUserRepository.findByRoleAndUser(creatorUser).orElseThrow().getServer(), appService.getCurrentUserEntity()).orElse(null);
        if (serverUser == null){
            serverUser = new ServerUserEntity
                    (server, user, ServerRole.USER);
            serverUserRepository.save(serverUser);
        }
        model.addAttribute("serverUser", serverUser);
        return "app-page";
    }

    @GetMapping("/add/article")
    public String getAddArticlePage(Model model,
                                    @RequestParam(name="user") Long userId
    ){
        model.addAttribute("server",serverUserRepository.findByRoleAndUser(userRepository.findAllById(userId).get()).orElseThrow().getServer());
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
