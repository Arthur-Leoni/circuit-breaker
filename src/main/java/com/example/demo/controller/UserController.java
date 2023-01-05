package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserFeignClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value="user controller")
@CrossOrigin(origins="*")
@Slf4j
public class UserController {

    @Autowired
    private UserFeignClientService userFeignClientService;

    @GetMapping("/getAll")
    @ApiOperation(value="get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userFeignClientService.getAllUsers(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Error trying to get users with feignClient", e);
            throw e;
        }

    }
}
