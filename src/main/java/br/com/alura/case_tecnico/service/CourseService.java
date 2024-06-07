package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.CourseRequestDTO;
import br.com.alura.case_tecnico.entity.course.Course;
import br.com.alura.case_tecnico.entity.user.User;
import br.com.alura.case_tecnico.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final UserService userService;

    public CourseService(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    public Page<Course> findAllOrByStatus(Byte status, Pageable pageable) {
        return this.courseRepository.findAllOrByStatus(status, pageable);
    }

    public Optional<Course> findByCode(String courseCode) {
        return this.courseRepository.findByCode(courseCode);
    }

    public void saveCourse(Course course) {
        this.courseRepository.save(course);
    }

    public void createCourse(CourseRequestDTO body) throws Exception {
        validateCourseCode(body.courseCode());
        User instructor = validateInstructor(body.instructorEmail(), body.instructorUsername());

        Course newCourse = buildCourse(body, instructor);
        this.saveCourse(newCourse);
    }

    private void validateCourseCode(String courseCode) throws Exception {
        if (this.findByCode(courseCode).isPresent()) {
            throw new Exception("Course code already exists");
        }
    }

    private User validateInstructor(String email, String username) throws Exception {
        User instructor = this.userService.findByEmailAndUsername(email, username);

        if (instructor == null) {
            throw new Exception("Instructor not found");
        }

        if (!"ROLE_INSTRUCTOR".equals(instructor.getRole().getRoleName())) {
            throw new Exception("User is not an instructor");
        }

        return instructor;
    }

    private Course buildCourse(CourseRequestDTO body, User instructor) {
        Course newCourse = new Course();
        newCourse.setCourseName(body.courseName());
        newCourse.setCode(body.courseCode());
        newCourse.setDescription(body.description());
        newCourse.setStatus((byte) 1);
        newCourse.setInstructor(instructor);
        newCourse.setCreatedAt(LocalDate.now());
        newCourse.setInactivatedAt(null);
        return newCourse;
    }

    public void deactivateCourseByCode(String courseCode) throws Exception {
        Course course = this.courseRepository.findByCode(courseCode).orElseThrow(() -> new Exception("Course not found"));

        if (course != null) {
            this.courseRepository.deactivateCourseByCode(courseCode);
        }
    }

}
