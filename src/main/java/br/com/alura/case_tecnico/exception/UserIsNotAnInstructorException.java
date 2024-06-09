package br.com.alura.case_tecnico.exception;

public class UserIsNotAnInstructorException extends RuntimeException {

    public UserIsNotAnInstructorException() {
        super("User is not an instructor");
    }

}
