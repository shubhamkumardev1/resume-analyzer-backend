package com.shubham.resumeAnalyzer.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Invalid Format")
    @NotBlank(message = " Email is required!")
    private String email;

    @NotBlank(message = "Password id required!")
    private String password;
}
