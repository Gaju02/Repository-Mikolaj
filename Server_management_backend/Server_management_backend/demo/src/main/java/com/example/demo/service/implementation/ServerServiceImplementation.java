package com.example.demo.service.implementation;

import com.example.demo.Repo.ServerRepo;
import com.example.demo.enumeration.Status;
import com.example.demo.model.Server;
import com.example.demo.service.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements Service {
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new Server : {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }
    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server : {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        return null;
    }

    @Override
    public Server get(Long id) {
        return null;
    }

    @Override
    public Server update(Server server) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
    private String setServerImageUrl() {
        return null;
    }
}
