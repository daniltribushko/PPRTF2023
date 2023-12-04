package com.example.pprtf2023.controllers;

import com.example.pprtf2023.models.dtos.request.CreateNewUserRequest;
import com.example.pprtf2023.models.dtos.request.JwtRequest;
import com.example.pprtf2023.services.security.SecurityAuthService;
import com.example.pprtf2023.services.security.SecurityUserService;
import com.example.pprtf2023.services.utils.JwtTokenUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final SecurityAuthService authService;

    @Autowired
    public AuthController(
            SecurityAuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@Valid JwtRequest request){
        return authService.createAuthToken(request);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@Valid CreateNewUserRequest request) {
        return authService.createNewUser(request);
    }
}
