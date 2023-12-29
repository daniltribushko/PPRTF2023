package com.example.pprtf2023.exceptions;

public class FileByIdNotFoundException extends GlobalAppException{
    public FileByIdNotFoundException(Long id) {
        super(404, "File with " + id + " id not found");
    }
}
