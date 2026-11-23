package com.example.blog.service;


import com.example.blog.entity.ServerEntity;
import com.example.blog.repository.ServerRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {
    private ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public ServerEntity getServerById(Long serverId) {
        return serverRepository.getServerEntitiesById(serverId).orElseThrow(()->new IllegalArgumentException("not found server"));
    }

    public void addServer(ServerEntity serverEntity) {
        serverRepository.save(serverEntity);
    }
}
