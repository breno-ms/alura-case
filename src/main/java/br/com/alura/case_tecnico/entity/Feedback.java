package br.com.alura.case_tecnico.entity;

import br.com.alura.case_tecnico.dto.FeedbackRequestDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", referencedColumnName = "id")
    private Enrollment enrollment;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Feedback(Integer id, Enrollment enrollment, Integer rating, String comment, LocalDate createdAt) {
        this.id = id;
        this.enrollment = enrollment;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Feedback(FeedbackRequestDTO feedbackRequestDTO, Enrollment enrollment) {
        this.enrollment = enrollment;
        this.rating = feedbackRequestDTO.rating();
        this.comment = feedbackRequestDTO.comment();
        this.createdAt = LocalDate.now();
    }

    public Feedback() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) && Objects.equals(enrollment, feedback.enrollment) && Objects.equals(rating, feedback.rating) && Objects.equals(comment, feedback.comment) && Objects.equals(createdAt, feedback.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enrollment, rating, comment, createdAt);
    }

}
