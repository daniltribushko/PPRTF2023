package com.example.pprtf2023.exceptions;

public class FileIsEmptyFileException extends GlobalAppException{
    public FileIsEmptyFileException() {
        super(409, "File is empty");
    }
}
