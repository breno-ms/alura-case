package br.com.alura.case_tecnico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@Valid
public record FeedbackRequestDTO (
    @NotNull(message = "Enrollment id cannot be null")
    Integer enrollmentId,

    @NotNull(message = "Rating cannot be null")
    @Range(min = 0, max = 10, message = "Rating must be an integer between 0 and 10")
    Integer rating,

    @NotBlank(message = "Comment cannot be blank")
    @NotEmpty(message = "Comment cannot be empty")
    @NotNull(message = "Comment cannot be null")
    String comment
    ) {}
