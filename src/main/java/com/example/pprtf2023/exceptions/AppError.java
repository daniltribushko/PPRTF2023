package com.example.pprtf2023.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class AppError {
    private int status;
    private String message;
    private Date timestamp;

    public AppError(int status, String message){
        this.status = status;
        this.message = message;
        timestamp = new Date();
    }
}
