package br.com.alura.case_tecnico.exception;

public class InstructorNotFoundException extends RuntimeException {

    public InstructorNotFoundException() {
        super("Instructor not found");
    }

}
