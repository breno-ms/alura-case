package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.dto.EnrollmentRequestDTO;
import br.com.alura.case_tecnico.dto.EnrollmentResponseDTO;
import br.com.alura.case_tecnico.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/create-enrollment")
    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(@RequestBody @Valid EnrollmentRequestDTO enrollmentRequestDTO) throws Exception {
        EnrollmentResponseDTO enrollmentResponseDTO = this.enrollmentService.createEnrollment(enrollmentRequestDTO);
        return new ResponseEntity<>(enrollmentResponseDTO, HttpStatus.OK);
    }

}
