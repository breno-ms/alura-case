package br.com.alura.case_tecnico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Valid
public record EnrollmentRequestDTO (
        @NotBlank(message = "Username cannot be blank")
        @NotNull(message = "Username cannot be null")
        @Size(min = 1, max = 20, message = "Username between 1 and 20 characters")
        @Pattern(regexp = "^[a-z]+$", message = "Username must contain only lowercase letters, no numerals or spaces")
        String username,

        @NotBlank
        @NotNull
        @Size(min = 1, max = 10, message = "Course name between 1 and 10 characters")
        @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Invalid course code")
        String courseCode) { }
