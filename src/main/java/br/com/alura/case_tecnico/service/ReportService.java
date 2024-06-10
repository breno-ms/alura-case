package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.model.CourseNpsReport;
import br.com.alura.case_tecnico.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<CourseNpsReport> getCoursesWithNPS() {
        List<CourseNpsReport> coursesWithNPS = reportRepository.findCoursesWithNPS();
        return coursesWithNPS;
    }

}
