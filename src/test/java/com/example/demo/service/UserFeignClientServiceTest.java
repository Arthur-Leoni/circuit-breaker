package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.factory.UserFactory;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Slf4j
@DirtiesContext
public class UserFeignClientServiceTest {

    @InjectMocks
    UserFeignClientService userService;

    @Mock
    UserFeignClient userFeignClient;

    protected CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();


    @Test
    void return_users_when_userFeignClient_return_users (){
        List<User> expectedReturn = UserFactory.createUserList();

        Optional<List<User>> userList = Optional.of(UserFactory.createUserList());
        when(userFeignClient.getUsers()).thenReturn(userList);

        assertEquals(expectedReturn,userService.getAllUsers());
        verify(userFeignClient).getUsers();
    }

    @Test
    void throws_exception_when_userFeignClient_return_null (){
        when(userFeignClient.getUsers()).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.getAllUsers();
        });

        verify(userFeignClient).getUsers();
    }
}
