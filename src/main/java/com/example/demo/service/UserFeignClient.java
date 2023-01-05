package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "user", url="http://localhost:8080/user/getAll")
public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "")
    Optional<List<User>> getUsers();
}
