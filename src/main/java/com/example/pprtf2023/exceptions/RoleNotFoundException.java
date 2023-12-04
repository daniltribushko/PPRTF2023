package com.example.pprtf2023.exceptions;

public class RoleNotFoundException extends GlobalAppException{
    public RoleNotFoundException(String name) {
        super(404, "Role " + name + " not found");
    }
}
