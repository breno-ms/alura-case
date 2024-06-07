package br.com.alura.case_tecnico.dto;

import jakarta.validation.constraints.*;

public record CourseRequestDTO (
        @NotBlank(message = "Course name cannot be blank")
        @NotEmpty(message = "Course name cannot be empty")
        @NotNull(message = "Course name cannot be null")
        @Size(min = 5, max = 100, message = "Course name between 1 and 100 characters")
        @Pattern(regexp = "^[a-z]+(?:-[a-z]+)*$", message = "Invalid course code")
        String courseName,

        @NotBlank(message = "Course code cannot be blank")
        @NotEmpty(message = "Course code cannot be empty")
        @NotNull(message = "Course code cannot be null")
        @Size(min = 1, max = 10, message = "Course name between 1 and 10 characters")
        String courseCode,

        @NotBlank(message = "Instructor name cannot be blank")
        @NotEmpty(message = "Instructor name cannot be empty")
        @NotNull(message = "Instructor name cannot be null")
        @Size(min = 1, max = 20, message = "Instructor username between 1 and 20 characters")
        @Pattern(regexp = "^[a-z]+$", message = "Username must contain only lowercase letters, no numerals or spaces")
        String instructorUsername,

        @NotBlank(message = "Instructor email cannot be blank")
        @NotEmpty(message = "Instructor email cannot be empty")
        @NotNull(message = "Instructor email cannot be null")
        @Email(message = "Invalid email format")
        String instructorEmail,

        @NotBlank(message = "Description cannot be blank")
        @NotEmpty(message = "Description cannot be empty")
        @NotNull(message = "Description cannot be null")
        @Size(min = 1, max = 255, message = "Description between 1 and 255 characters")
        String description) {
}
