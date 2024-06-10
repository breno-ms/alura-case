package br.com.alura.case_tecnico.entity;

import br.com.alura.case_tecnico.dto.CourseRequestDTO;
import br.com.alura.case_tecnico.dto.CourseResponseDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @Column(name = "course_name")
    private String courseName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private User instructor;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Byte status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "inactivated_at")
    private LocalDate inactivatedAt;

    public Course(CourseRequestDTO courseRequestDTO, User instructor) {
        this.courseName = courseRequestDTO.courseName();
        this.code = courseRequestDTO.courseCode();
        this.description = courseRequestDTO.description();
        this.status = (byte) 1;
        this.instructor = instructor;
        this.createdAt = LocalDate.now();
        this.inactivatedAt = null;
    }

    public Course() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructorId) {
        this.instructor = instructorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getInactivatedAt() {
        return inactivatedAt;
    }

    public void setInactivatedAt(LocalDate inactivatedAt) {
        this.inactivatedAt = inactivatedAt;
    }

    public CourseResponseDTO convertToDto() {
        return new CourseResponseDTO(
                this.getCode(),
                this.getCourseName(),
                this.getInstructor().convertToInstructorDto(),
                this.getDescription(),
                this.getStatus(),
                this.getCreatedAt(),
                this.getInactivatedAt()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(code, course.code) && Objects.equals(courseName, course.courseName) && Objects.equals(instructor, course.instructor) && Objects.equals(description, course.description) && Objects.equals(status, course.status) && Objects.equals(createdAt, course.createdAt) && Objects.equals(inactivatedAt, course.inactivatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, courseName, instructor, description, status, createdAt, inactivatedAt);
    }

}
