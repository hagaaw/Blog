package com.example.blog.controller;

import com.example.blog.repository.UserRepository;
import com.example.blog.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String getRegistrationPage(){
        return "registration-page";
    }

    @PostMapping
    public String saveUser(
            @RequestParam(name="name") String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name="password") String password
    ){

    }
}
