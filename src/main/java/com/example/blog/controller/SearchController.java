package com.example.blog.controller;

import com.example.blog.entity.UserEntity;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/find")
public class SearchController {
    private UserRepository userRepository;

    @Autowired
    public SearchController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getFindPage(Model model){
        List<UserEntity> blogsUsers = userRepository.findAll();
        model.addAttribute("users", blogsUsers);
        return "find-blog-page";
    }
}
