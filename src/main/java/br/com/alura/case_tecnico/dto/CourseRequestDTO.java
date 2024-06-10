package br.com.alura.case_tecnico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Valid
public record CourseRequestDTO (
        @NotBlank(message = "Course name cannot be blank")
        @NotNull(message = "Course name cannot be null")
        @Size(min = 3, max = 100, message = "Course name between 1 and 100 characters")
        String courseName,

        @NotBlank(message = "Course code cannot be blank")
        @NotNull(message = "Course code cannot be null")
        @Size(min = 1, max = 10, message = "Course name between 1 and 10 characters")
        @Pattern(regexp = "^[a-z]+(-[a-z]+)*$", message = "Invalid course code")
        String courseCode,

        @NotBlank(message = "Instructor name cannot be blank")
        @NotNull(message = "Instructor name cannot be null")
        @Size(min = 1, max = 20, message = "Instructor username between 1 and 20 characters")
        @Pattern(regexp = "^[a-z]+$", message = "Username must contain only lowercase letters, no numerals or spaces")
        String instructorUsername,

        @NotBlank(message = "Instructor email cannot be blank")
        @NotNull(message = "Instructor email cannot be null")
        @Email(message = "Invalid email format")
        String instructorEmail,

        @NotBlank(message = "Description cannot be blank")
        @NotNull(message = "Description cannot be null")
        @Size(min = 1, max = 255, message = "Description between 1 and 255 characters")
        String description) {
}
