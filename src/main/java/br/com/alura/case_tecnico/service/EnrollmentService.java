package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.EnrollmentRequestDTO;
import br.com.alura.case_tecnico.entity.course.Course;
import br.com.alura.case_tecnico.entity.enrollment.Enrollment;
import br.com.alura.case_tecnico.entity.user.User;
import br.com.alura.case_tecnico.repository.CourseRepository;
import br.com.alura.case_tecnico.repository.EnrollmentRepository;
import br.com.alura.case_tecnico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public boolean createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) throws Exception {
        // TODO: criar exception personalizada
        User user = this.userRepository
                .findByUsername(enrollmentRequestDTO.username())
                .orElseThrow(() -> new Exception("User not found"));

        // TODO: criar exception personalizada
        Course course = this.courseRepository
                .findByCode(enrollmentRequestDTO.courseCode())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        boolean isCourseActive = this.isCourseActive(course.getCode());
        boolean isUserNotEnrolledInCourse = this.isUserNotEnrolledInCourse(user.getId(), course.getCode());

        if (isCourseActive && isUserNotEnrolledInCourse) {
            Enrollment enrollment = new Enrollment(user, course);
            this.enrollmentRepository.save(enrollment);
            return true;
        }

        return false;
    }

    public boolean isCourseActive(String courseCode) {
        Course course = this.courseRepository.findByCode(courseCode).orElseThrow();
        return course.getStatus() == 1;
    }

    public boolean isUserNotEnrolledInCourse(Integer userId, String courseCode) {
        return !enrollmentRepository.existsByUserIdAndCourseCode(userId, courseCode);
    }

}
