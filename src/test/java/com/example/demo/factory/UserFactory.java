package com.example.demo.factory;

import com.example.demo.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {
    public static final String USER_AGE = "29";
    public static final String USER_NAME = "ARTHUR";
    public static final String USER2_AGE = "25";
    public static final String USER2_NAME = "PEDRO";

    public static List<User> createUserList(){
        List<User> userList = new ArrayList<User>();

        User user1 = User.builder()
                .age(USER_AGE)
                .name(USER_NAME)
                .build();

        User user2 = User.builder()
                .age(USER2_AGE)
                .name(USER2_NAME)
                .build();

        userList.add(user1);
        userList.add(user2);
        return  userList;
    }
}
