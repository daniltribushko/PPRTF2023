package com.example.pprtf2023.exceptions;

public class UserByUserNameNotFoundException extends GlobalAppException{
    public UserByUserNameNotFoundException(String userName) {
        super(404, "User " + userName + " not found exception");
    }
}
