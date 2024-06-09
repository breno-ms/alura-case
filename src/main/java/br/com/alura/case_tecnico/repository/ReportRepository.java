package br.com.alura.case_tecnico.repository;

import br.com.alura.case_tecnico.entity.course.Course;
import br.com.alura.case_tecnico.entity.report.CourseNpsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Course, String> {

    @Query("SELECT c.code AS courseCode, c.courseName AS courseName, " +
            "COUNT(e.id) AS enrollmentsCount, " +
            "AVG(f.rating) AS averageRating " +
            "FROM Course c " +
            "JOIN Enrollment e ON c.code = e.course.code " +
            "LEFT JOIN Feedback f ON e.id = f.enrollment.id " +
            "WHERE c.status = 1 " +
            "GROUP BY c.code, c.courseName " +
            "HAVING COUNT(e.id) > 4")
    List<CourseNPSProjection> findCoursesWithNPS();

    interface CourseNPSProjection {
        String getCourseCode();
        String getCourseName();
        Long getEnrollmentsCount();
        Double getAverageRating();
    }

}
