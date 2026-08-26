package com.example.blog.repository;

import com.example.blog.entity.ArticleEntity;
import com.example.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> findByUserEntity(UserEntity userEntity);
}
