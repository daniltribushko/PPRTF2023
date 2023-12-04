package com.example.pprtf2023.exceptions;

public class UserAlreadyExistException extends GlobalAppException{
    public UserAlreadyExistException(String userName) {
        super(409, "User " + userName + " already exists");
    }
}
