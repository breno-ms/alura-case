package br.com.alura.case_tecnico.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {
        super("Course not found");
    }

}
