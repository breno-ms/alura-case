package br.com.alura.case_tecnico.exception;

public class EnrollmentNotFoundException extends RuntimeException {

    public EnrollmentNotFoundException() {
        super("Enrollment not found");
    }

}
