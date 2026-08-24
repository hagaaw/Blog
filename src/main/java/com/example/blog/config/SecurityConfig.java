package com.example.blog.config;

import com.example.blog.entity.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(us->{
                    us
                            .requestMatchers("/app").hasAnyRole(UserRole.USER.name(),UserRole.ADMIN.name())
                            .anyRequest().permitAll();
                })
                .formLogin(login->{
                    login.loginPage("/login")
                            .usernameParameter("name")
                            .defaultSuccessUrl("/app").permitAll();
                })
                .logout(LogoutConfigurer::permitAll)
                .build();
    }
}
