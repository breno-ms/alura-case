package br.com.alura.case_tecnico.dto;

import java.time.LocalDate;

public record CourseResponseDTO (
        String code,
        String courseName,
        InstructorDTO instructor,
        String description,
        Byte status,
        LocalDate createdAt,
        LocalDate inactivatedAt) { }
