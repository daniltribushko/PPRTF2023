package com.example.pprtf2023.models.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CreateNewUserRequest {
    @NotBlank(message = "Username must not be blank")
    private String userName;
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Surename must not be blank")
    private String sureName;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Password not be blank")
    private String password;
    @NotBlank(message = "ConfirmPassword not be blank")
    private String confirmPassword;

}
