package com.example.pprtf2023.services.security;

import com.example.pprtf2023.exceptions.AppError;
import com.example.pprtf2023.models.dtos.request.CreateNewUserRequest;
import com.example.pprtf2023.models.dtos.request.JwtRequest;
import com.example.pprtf2023.models.dtos.response.JwtResponse;
import com.example.pprtf2023.services.utils.JwtTokenUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Сервис для авторизации пользователей
 */
@Service
public class SecurityAuthService {
    private final SecurityUserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public SecurityAuthService(SecurityUserService userService,
                               JwtTokenUtils jwtTokenUtils,
                               AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> createAuthToken(@RequestBody @Valid JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUserName(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(),
                            "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        String token = jwtTokenUtils.generateToken(userService.loadUserByUsername(
                authRequest.getUserName())
        );
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody @Valid CreateNewUserRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUserName(request.getUserName()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        userService.createNewUser(request);
        return ResponseEntity.ok(request.getSureName() + " " + request.getName());
    }
}
