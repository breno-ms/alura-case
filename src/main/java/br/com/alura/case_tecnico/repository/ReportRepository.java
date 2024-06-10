package br.com.alura.case_tecnico.repository;

import br.com.alura.case_tecnico.entity.Course;
import br.com.alura.case_tecnico.model.CourseNpsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Course, String> {

    @Query("""
            SELECT new br.com.alura.case_tecnico.model.CourseNpsReport(
                c.code, 
                c.courseName, 
                COUNT(e.id) AS totalEnrollments,
                SUM(CASE WHEN f.rating >= 9 THEN 1 ELSE 0 END) AS promoters,
                SUM(CASE WHEN f.rating <= 6 THEN 1 ELSE 0 END) AS detractors,
                COUNT(f.id) AS totalFeedbacks
            )
            FROM Course c
            JOIN Enrollment e ON c.code = e.course.code
            JOIN Feedback f ON f.enrollment.id = e.id
            WHERE c.status = 1
            GROUP BY c.code, c.courseName
            HAVING COUNT(e.id) > 4""")
    List<CourseNpsReport> findCoursesWithNPS();

}
