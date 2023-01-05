package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.factory.UserFactory;
import com.example.demo.service.UserFeignClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserFeignClientService userFeignClientService;

    @Test
    void return_users_when_userFeignClientService_return_users(){
        List<User> userList = UserFactory.createUserList();
        ResponseEntity<List<User>> expectedReturn = new ResponseEntity<>(userList, HttpStatus.OK);

        when(userFeignClientService.getAllUsers()).thenReturn(userList);

        assertEquals(expectedReturn, userController.getAllUsers());
        verify(userFeignClientService).getAllUsers();
    }

    @Test
    void throws_exception_when_userFeignClientService_throws_exception(){
        when(userFeignClientService.getAllUsers()).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            userController.getAllUsers();
        });

        verify(userFeignClientService).getAllUsers();
    }

}
