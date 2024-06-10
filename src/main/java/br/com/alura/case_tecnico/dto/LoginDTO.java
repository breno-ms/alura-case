package br.com.alura.case_tecnico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Valid
public record LoginDTO (
        @NotBlank(message = "Email cannot be blank")
        @NotNull(message = "Email cannot be null")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @NotNull(message = "Password cannot be null")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        String password) { }
