package br.com.alura.case_tecnico.repository;

import br.com.alura.case_tecnico.entity.enrollment.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    boolean existsByUserIdAndCourseCode(Integer userId, String courseCode);

}
