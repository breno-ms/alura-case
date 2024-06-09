package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.EnrollmentRequestDTO;
import br.com.alura.case_tecnico.dto.EnrollmentResponseDTO;
import br.com.alura.case_tecnico.entity.course.Course;
import br.com.alura.case_tecnico.entity.enrollment.Enrollment;
import br.com.alura.case_tecnico.entity.user.User;
import br.com.alura.case_tecnico.exception.*;
import br.com.alura.case_tecnico.repository.CourseRepository;
import br.com.alura.case_tecnico.repository.EnrollmentRepository;
import br.com.alura.case_tecnico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) throws Exception {
        User user = findUserByUsername(enrollmentRequestDTO.username());
        Course course = findCourseByCode(enrollmentRequestDTO.courseCode());

        validateCourseIsActive(course);
        validateUserNotEnrolledInCourse(user.getId(), course.getCode());

        Enrollment enrollment = new Enrollment(user, course);
        EnrollmentResponseDTO enrollmentResponseDTO = new EnrollmentResponseDTO(
                course.getCode(),
                course.getInstructor().getUsername(),
                enrollmentRequestDTO.username());

        this.enrollmentRepository.save(enrollment);

        return enrollmentResponseDTO;
    }

    private User findUserByUsername(String username) throws UserNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    private Course findCourseByCode(String courseCode) throws CourseNotFoundException {
        return this.courseRepository
                .findByCode(courseCode)
                .orElseThrow(CourseNotFoundException::new);
    }

    public Enrollment findById(Integer enrollmentId) throws EnrollmentNotFoundException {
        return this.enrollmentRepository
                .findById(enrollmentId)
                .orElseThrow(EnrollmentNotFoundException::new);
    }

    private void validateCourseIsActive(Course course) throws CourseIsInactiveException {
        if (course.getStatus() != 1) {
            throw new CourseIsInactiveException();
        }
    }

    private void validateUserNotEnrolledInCourse(Integer userId, String courseCode) throws UserAlreadyEnrolledInCourseException {
        if (this.enrollmentRepository.existsByUserIdAndCourseCode(userId, courseCode)) {
            throw new UserAlreadyEnrolledInCourseException();
        }
    }

}
