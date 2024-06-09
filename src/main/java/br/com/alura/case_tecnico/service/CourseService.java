package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.CourseRequestDTO;
import br.com.alura.case_tecnico.dto.CourseResponseDTO;
import br.com.alura.case_tecnico.dto.InstructorDTO;
import br.com.alura.case_tecnico.dto.PageDTO;
import br.com.alura.case_tecnico.entity.course.Course;
import br.com.alura.case_tecnico.entity.user.User;
import br.com.alura.case_tecnico.exception.CourseCodeAlreadyExistsException;
import br.com.alura.case_tecnico.exception.CourseNotFoundException;
import br.com.alura.case_tecnico.exception.InstructorNotFoundException;
import br.com.alura.case_tecnico.exception.UserIsNotAnInstructorException;
import br.com.alura.case_tecnico.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    public CourseService(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    public PageDTO<CourseResponseDTO> findAllOrByStatus(Byte status, Pageable pageable) {
        Page<Course> courses = courseRepository.findAllOrByStatus(status, pageable);

        List<CourseResponseDTO> courseDTOs = courses.getContent().stream()
                .map(course -> new CourseResponseDTO(
                        course.getCode(),
                        course.getCourseName(),
                        new InstructorDTO(course.getInstructor().getUsername(), course.getInstructor().getEmail()),
                        course.getDescription(),
                        course.getStatus(),
                        course.getCreatedAt(),
                        course.getInactivatedAt()))
                .collect(Collectors.toList());

        return new PageDTO<>(
                courseDTOs,
                courses.getNumber(),
                courses.getSize(),
                courses.getTotalElements(),
                courses.getTotalPages()
        );
    }

    public Optional<Course> findByCode(String courseCode) {
        return this.courseRepository.findByCode(courseCode);
    }

    public CourseResponseDTO createCourse(CourseRequestDTO body) throws Exception {
        validateCourseCode(body.courseCode());
        User instructor = validateInstructor(body.instructorEmail(), body.instructorUsername());

        Course newCourse = buildCourse(body, instructor);
        this.courseRepository.save(newCourse);

        return new CourseResponseDTO(
                newCourse.getCode(),
                newCourse.getCourseName(),
                new InstructorDTO(newCourse.getInstructor().getUsername(), newCourse.getInstructor().getEmail()),
                newCourse.getDescription(),
                newCourse.getStatus(),
                newCourse.getCreatedAt(),
                newCourse.getInactivatedAt());
    }

    public CourseResponseDTO deactivateCourseByCode(String courseCode) throws CourseNotFoundException {
        Course course = this.courseRepository
                .findByCode(courseCode)
                .orElseThrow(CourseNotFoundException::new);

        if (course != null) {
            this.courseRepository.deactivateCourseByCode(courseCode);
        }

        return new CourseResponseDTO(
                course.getCode(),
                course.getCourseName(),
                new InstructorDTO(course.getInstructor().getUsername(), course.getInstructor().getEmail()),
                course.getDescription(),
                course.getStatus(),
                course.getCreatedAt(),
                LocalDate.now());
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

    private void validateCourseCode(String courseCode) throws CourseNotFoundException {
        if (this.findByCode(courseCode).isPresent()) {
            throw new CourseCodeAlreadyExistsException();
        }
    }

    private User validateInstructor(String email, String username) throws Exception {
        User instructor = this.userService.findByEmailAndUsername(email, username);

        if (instructor == null) {
            throw new InstructorNotFoundException();
        }

        if (!"ROLE_INSTRUCTOR".equals(instructor.getRole().getRoleName())) {
            throw new UserIsNotAnInstructorException();
        }

        return instructor;
    }

}
