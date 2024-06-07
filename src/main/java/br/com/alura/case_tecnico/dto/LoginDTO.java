package br.com.alura.case_tecnico.dto;

import jakarta.validation.constraints.*;

public record LoginDTO (
        @NotBlank(message = "Email cannot be blank")
        @NotEmpty(message = "Email cannot be empty")
        @NotNull(message = "Email cannot be null")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @NotEmpty(message = "Password cannot be empty")
        @NotNull(message = "Password cannot be null")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        String password) { }
