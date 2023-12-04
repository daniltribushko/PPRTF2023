package com.example.pprtf2023.models.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class JwtRequest {
    @NotBlank(message = "Username must not be blank")
    private String userName;
    @NotBlank(message = "Password must not be blank")
    private String password;

}
