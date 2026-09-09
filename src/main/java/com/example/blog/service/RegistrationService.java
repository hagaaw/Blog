package com.example.blog.service;

import com.example.blog.entity.*;
import com.example.blog.repository.ServerUserRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final ServerUserRepository serverUserRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ServerService serverService;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, ServerService serverService, ServerUserRepository serverUserRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.serverService = serverService;
        this.serverUserRepository = serverUserRepository;
    }

    public void saveUser(String name, String email, String password) {
        var server = new ServerEntity();
        var user = new UserEntity
                (email, name, passwordEncoder.encode(password), UserRole.USER);
        serverService.addServer(server);
        userRepository.save(user);
        serverUserRepository.save(new ServerUserEntity
                (server, user, ServerRole.CREATOR));
    }
}
