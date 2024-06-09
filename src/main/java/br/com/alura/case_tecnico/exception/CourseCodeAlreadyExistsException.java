package br.com.alura.case_tecnico.exception;

public class CourseCodeAlreadyExistsException extends RuntimeException {

    public CourseCodeAlreadyExistsException() {
        super("Course code already exists");
    }

}
