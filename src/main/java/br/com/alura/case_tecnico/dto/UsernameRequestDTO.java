package br.com.alura.case_tecnico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Valid
public record UsernameRequestDTO (
        @NotBlank(message = "Username cannot be blank")
        @NotEmpty(message = "Username cannot be empty")
        @NotNull(message = "Username cannot be null")
        @Size(min = 1, max = 20, message = "Username between 1 and 20 characters")
        @Pattern(regexp = "^[a-z]+$", message = "Username must contain only lowercase letters, no numerals or spaces")
        String username) { }
