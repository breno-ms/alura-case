package br.com.alura.case_tecnico.entity.enrollment;

import br.com.alura.case_tecnico.entity.course.Course;
import br.com.alura.case_tecnico.entity.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private final User user;

    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "code")
    private final Course course;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    public Enrollment(User user, Course course) {
        this.user = user;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(course, that.course) && Objects.equals(enrollmentDate, that.enrollmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, course, enrollmentDate);
    }

}
