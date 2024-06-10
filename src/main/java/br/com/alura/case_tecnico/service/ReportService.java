package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.entity.Course;
import br.com.alura.case_tecnico.model.CourseNpsReport;
import br.com.alura.case_tecnico.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
