package com.epam.kozitski.domain.util;

import com.epam.kozitski.domain.User;

public class Users {
    private static final long DEFAULT_USER_ID = 1;
    private static final String DEFAULT_USER_LOGIN = "andrei";
    private static final String DEFAULT_USER_PASSWORD = "12345";

    public static User createDefaultUser(){
        return User
                .builder()
                .id(DEFAULT_USER_ID)
                .login(DEFAULT_USER_LOGIN)
                .password(DEFAULT_USER_PASSWORD)
                .build();
    }

}
