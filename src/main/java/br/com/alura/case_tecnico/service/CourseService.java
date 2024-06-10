package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.CourseRequestDTO;
import br.com.alura.case_tecnico.dto.CourseResponseDTO;
import br.com.alura.case_tecnico.entity.Course;
import br.com.alura.case_tecnico.entity.User;
import br.com.alura.case_tecnico.exception.CourseCodeAlreadyExistsException;
import br.com.alura.case_tecnico.exception.CourseNotFoundException;
import br.com.alura.case_tecnico.exception.UserIsNotAnInstructorException;
import br.com.alura.case_tecnico.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Page<CourseResponseDTO> findAllOrByStatus(Byte status, Pageable pageable) {
        Page<Course> courses = courseRepository.findAllOrByStatus(status, pageable);

        if (courses.isEmpty()) {
            return Page.empty();
        }

        List<CourseResponseDTO> courseDTOs = courses.getContent().stream()
                .map(Course::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(
                courseDTOs,
                pageable,
                courses.getTotalElements()
        );
    }

    public Optional<Course> findByCode(String courseCode) {
        return this.courseRepository.findByCode(courseCode);
    }

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) throws Exception {
        validateCourseCode(courseRequestDTO.courseCode());

        User instructor = validateInstructor(courseRequestDTO.instructorEmail(), courseRequestDTO.instructorUsername());
        Course newCourse = new Course(courseRequestDTO, instructor);

        this.courseRepository.save(newCourse);

        return newCourse.convertToDto();
    }

    public CourseResponseDTO deactivateCourseByCode(String courseCode) throws CourseNotFoundException {
        Course course = this.courseRepository
                .findByCode(courseCode)
                .orElseThrow(CourseNotFoundException::new);

        this.courseRepository.deactivateCourseByCode(courseCode);

        return course.convertToDto();
    }

    private void validateCourseCode(String courseCode) throws CourseNotFoundException {
        if (this.findByCode(courseCode).isPresent()) {
            throw new CourseCodeAlreadyExistsException();
        }
    }

    private User validateInstructor(String email, String username) {
        User user = this.userService.findByEmailAndUsername(email, username);

        if (!user.isInstructor()) {
            throw new UserIsNotAnInstructorException();
        }

        return user;
    }

}
