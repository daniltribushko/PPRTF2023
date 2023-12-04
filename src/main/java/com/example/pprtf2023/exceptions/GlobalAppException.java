package com.example.pprtf2023.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalAppException extends RuntimeException{
    private Integer status;
    private String message;
}
