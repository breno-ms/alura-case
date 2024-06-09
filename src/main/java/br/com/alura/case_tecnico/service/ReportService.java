package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.entity.report.CourseNpsReport;
import br.com.alura.case_tecnico.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<CourseNpsReport> getNPSReport() {
        List<ReportRepository.CourseNPSProjection> projections = reportRepository.findCoursesWithNPS();

        return projections.stream().map(projection -> {
            double nps = calculateNPS(projection.getAverageRating());
            return new CourseNpsReport(projection.getCourseCode(), projection.getCourseName(), projection.getEnrollmentsCount(), nps);
        }).collect(Collectors.toList());
    }

    private double calculateNPS(Double averageRating) {
        if (averageRating == null) return 0;
        return (averageRating - 5) * 20;
    }

}
