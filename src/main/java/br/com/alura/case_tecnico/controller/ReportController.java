package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.entity.report.CourseNpsReport;
import br.com.alura.case_tecnico.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/list-report")
    public ResponseEntity<List<CourseNpsReport>> getNPSReport() {
        List<CourseNpsReport> reports = this.reportService.getNPSReport();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

}
