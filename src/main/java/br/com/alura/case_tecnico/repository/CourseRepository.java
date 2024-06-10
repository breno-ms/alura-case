package br.com.alura.case_tecnico.repository;

import br.com.alura.case_tecnico.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("SELECT c FROM Course c WHERE (:status IS NULL OR c.status = :status)")
    Page<Course> findAllOrByStatus(@Param("status") Byte status, Pageable pageable);
    Optional<Course> findByCode(String code);

    @Modifying
    @Transactional
    @Query("UPDATE Course c SET c.status = 0, c.inactivatedAt = CURRENT_DATE WHERE c.code = :courseCode")
    void deactivateCourseByCode(@Param("courseCode") String courseCode);

}
