package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.dto.CourseRequestDTO;
import br.com.alura.case_tecnico.dto.CourseResponseDTO;
import br.com.alura.case_tecnico.dto.InstructorDTO;
import br.com.alura.case_tecnico.dto.PageDTO;
import br.com.alura.case_tecnico.entity.course.Course;
import br.com.alura.case_tecnico.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/findAllOrByStatus")
    public ResponseEntity<PageDTO<CourseResponseDTO>> findAllOrByStatus(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sort,
            @RequestParam(required = false) Byte status) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Course> courses = courseService.findAllOrByStatus(status, pageable);

        List<CourseResponseDTO> courseDTOs = courses.getContent().stream()
                .map(course ->
                        new CourseResponseDTO(
                        course.getCode(),
                        course.getCourseName(),
                        new InstructorDTO(course.getInstructor().getUsername(), course.getInstructor().getEmail()),
                        course.getDescription(),
                        course.getStatus(),
                        course.getCreatedAt(),
                        course.getInactivatedAt()))
                .collect(Collectors.toList());

        PageDTO<CourseResponseDTO> pageDTO = new PageDTO<>(
                courseDTOs,
                courses.getNumber(),
                courses.getSize(),
                courses.getTotalElements(),
                courses.getTotalPages()
        );

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @PostMapping("/createCourse")
    public ResponseEntity<?> createCourse(@RequestBody @Valid CourseRequestDTO body) {
        try {
            this.courseService.createCourse(body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/deactivateCourse/{courseCode}")
    public ResponseEntity<?> deactivateCourse(@PathVariable @Size(max = 10, message = "Code must be at most 10 characters") String courseCode) {
        try {
            this.courseService.deactivateCourseByCode(courseCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
