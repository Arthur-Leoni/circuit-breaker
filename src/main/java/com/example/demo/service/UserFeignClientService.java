package com.example.demo.service;

import com.example.demo.domain.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserFeignClientService {

    @Autowired
    private UserFeignClient userFeignClient;

    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "getAllUsersCache")
    public List<User> getAllUsers() {
            log.info("[UserFeignClientService] Call API to get all users");
            List<User> users;
        try{
            users = userFeignClient.getUsers().get();
        }catch (Exception e){
            log.info("[UserFeignClientService] Error to get users");
            throw e;
        }
        System.out.println("[UserFeignClientService] Adding in CACHE");
        addToCache(users);
        return users;
    }

    private void addToCache(List<User> users) {
        CACHE.clear();
        CACHE.addAll(users);
    }

    private List<User> CACHE = new ArrayList<>();

    private List<User> getAllUsersCache(Throwable e){
        log.info("[UserFeignClientService] Searching in CACHE");
        return CACHE;
    }
}
