package br.com.alura.case_tecnico.dto;

import jakarta.validation.constraints.*;

public record RegisterUserDTO(
        @NotBlank(message = "Username cannot be blank")
        @NotEmpty(message = "Username cannot be empty")
        @NotNull(message = "Username cannot be null")
        @Size(min = 1, max = 20, message = "Username between 1 and 20 characters")
        @Pattern(regexp = "^[a-z]+$", message = "Username must contain only lowercase letters, no numerals or spaces")
        String username,

        @NotBlank(message = "Email cannot be blank")
        @NotEmpty(message = "Email cannot be empty")
        @NotNull(message = "Email cannot be null")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @NotEmpty(message = "Password cannot be empty")
        @NotNull(message = "Password cannot be null")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        String password,

        @NotBlank(message = "Role cannot be blank")
        @NotEmpty(message = "Role cannot be empty")
        @NotNull(message = "Role cannot be null")
        @Pattern(regexp = "^(ADMIN|INSTRUCTOR|STUDENT)$", message = "Role must be either ADMIN, INSTRUCTOR, or STUDENT")
        String role
) { }
