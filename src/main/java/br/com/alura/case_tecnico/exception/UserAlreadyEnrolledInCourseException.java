package br.com.alura.case_tecnico.exception;

public class UserAlreadyEnrolledInCourseException extends RuntimeException {

    public UserAlreadyEnrolledInCourseException() {
        super("User is already enrolled in that course");
    }

}
