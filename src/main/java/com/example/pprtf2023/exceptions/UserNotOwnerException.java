package com.example.pprtf2023.exceptions;

public class UserNotOwnerException extends GlobalAppException{
    public UserNotOwnerException(String userName) {
        super(409, "User is not owner");
    }
}
