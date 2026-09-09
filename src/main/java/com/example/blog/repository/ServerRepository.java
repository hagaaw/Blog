package com.example.blog.repository;

import com.example.blog.entity.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServerRepository extends JpaRepository<ServerEntity, Long> {
    Optional<ServerEntity> getServerEntitiesById(Long serverId);
}
