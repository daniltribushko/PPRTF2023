package com.example.pprtf2023.controllers;

import com.example.pprtf2023.services.FileService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    @PutMapping("")
    public ResponseEntity<?> addFile(MultipartFile file, Principal principal){
        String result = fileService.saveFile(file, principal.getName());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id, Principal principal){
        Resource result = fileService.getFileById(id, principal.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
