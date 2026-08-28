package com.example.blog.repository;

import com.example.blog.entity.ServerEntity;
import com.example.blog.entity.ServerUserEntity;
import com.example.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ServerUserRepository extends JpaRepository<ServerUserEntity, Long> {
    Optional<ServerUserEntity> getServerUserEntitiesByServerAndUser(ServerEntity server, UserEntity user);

    @Query("SELECT s FROM ServerUserEntity s WHERE s.role = 'CREATOR' AND s.user = :user")
    Optional<ServerUserEntity> findByRoleAndUser(@Param("user") UserEntity user);
}
