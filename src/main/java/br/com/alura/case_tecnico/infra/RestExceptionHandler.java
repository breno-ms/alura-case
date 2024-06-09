package br.com.alura.case_tecnico.infra;

import br.com.alura.case_tecnico.exception.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    private ResponseEntity<RestErrorMessage> usernameAlreadyTakenHandler(UsernameAlreadyTakenException usernameAlreadyTakenException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, usernameAlreadyTakenException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> usernameNotFoundHandler(UserNotFoundException usernameNotFoundException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, usernameNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(UserIsNotAnInstructorException.class)
    private ResponseEntity<RestErrorMessage> userIsNotAnInstructorHandler(UserIsNotAnInstructorException userIsNotAnInstructorException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, userIsNotAnInstructorException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(InstructorNotFoundException.class)
    private ResponseEntity<RestErrorMessage> instructorNotFoundHandler(InstructorNotFoundException instructorNotFoundException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, instructorNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(CourseCodeAlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> courseCodeAlreadyExistsHandler(CourseCodeAlreadyExistsException courseCodeAlreadyExistsException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, courseCodeAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    private ResponseEntity<RestErrorMessage> courseNotFoundHandler(CourseNotFoundException courseNotFoundException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, courseNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(CourseIsInactiveException.class)
    private ResponseEntity<RestErrorMessage> courseIsInactiveHandler(CourseIsInactiveException courseIsInactiveException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, courseIsInactiveException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    private ResponseEntity<RestErrorMessage> enrollmentNotFoundHandler(EnrollmentNotFoundException enrollmentNotFoundException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, enrollmentNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(UserAlreadyEnrolledInCourseException.class)
    private ResponseEntity<RestErrorMessage> userAlreadyEnrolledInCourseHandler(UserAlreadyEnrolledInCourseException userAlreadyEnrolledInCourseException) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, userAlreadyEnrolledInCourseException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

}
