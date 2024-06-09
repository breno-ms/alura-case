package br.com.alura.case_tecnico.exception;

public class CourseIsInactiveException extends RuntimeException {

    public CourseIsInactiveException() {
        super("Course is not active");
    }

}
