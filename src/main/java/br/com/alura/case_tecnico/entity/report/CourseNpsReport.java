package br.com.alura.case_tecnico.entity.report;

import java.util.Objects;

public class CourseNpsReport {

    private String courseCode;
    private String courseName;
    private Long enrollmentsCount;
    private double nps;

    public CourseNpsReport(String courseCode, String courseName, Long enrollmentsCount, double nps) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.enrollmentsCount = enrollmentsCount;
        this.nps = nps;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getEnrollmentsCount() {
        return enrollmentsCount;
    }

    public void setEnrollmentsCount(Long enrollmentsCount) {
        this.enrollmentsCount = enrollmentsCount;
    }

    public double getNps() {
        return nps;
    }

    public void setNps(double nps) {
        this.nps = nps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseNpsReport that = (CourseNpsReport) o;
        return Double.compare(nps, that.nps) == 0 && Objects.equals(courseCode, that.courseCode) && Objects.equals(courseName, that.courseName) && Objects.equals(enrollmentsCount, that.enrollmentsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseName, enrollmentsCount, nps);
    }

}
