package com.example.blog.service;

import com.example.blog.entity.UserEntity;
import com.example.blog.entity.UserRole;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(String name, String email, String password) {

        userRepository.save(new UserEntity(email, name, passwordEncoder.encode(password), UserRole.USER));
    }
}
