package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.dto.EnrollmentRequestDTO;
import br.com.alura.case_tecnico.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/createEnrollment")
    public ResponseEntity<?> createEnrollment(@RequestBody @Valid EnrollmentRequestDTO body) throws Exception {
        boolean enrollmentCreated = this.enrollmentService.createEnrollment(body);
        System.out.println(body);

        if (enrollmentCreated) {
            return new ResponseEntity<>("Enrollment created", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Enrollment cannot be created", HttpStatus.BAD_REQUEST);
        }
    }

}
