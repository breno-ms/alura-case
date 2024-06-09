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

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/find-all-or-by-status")
    public ResponseEntity<PageDTO<CourseResponseDTO>> findAllOrByStatus(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sort,
            @RequestParam(required = false) Byte status) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        PageDTO<CourseResponseDTO> pageDTO = courseService.findAllOrByStatus(status, pageable);

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @PostMapping("/create-course")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody @Valid CourseRequestDTO body) throws Exception {
        CourseResponseDTO courseResponseDTO = this.courseService.createCourse(body);
        return new ResponseEntity<>(courseResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/deactivate-course/{courseCode}")
    public ResponseEntity<CourseResponseDTO> deactivateCourse(@PathVariable @Size(max = 10, message = "Code must be at most 10 characters") String courseCode) throws Exception {
        CourseResponseDTO courseResponseDTO = this.courseService.deactivateCourseByCode(courseCode);
        return new ResponseEntity<>(courseResponseDTO, HttpStatus.OK);
    }

}
