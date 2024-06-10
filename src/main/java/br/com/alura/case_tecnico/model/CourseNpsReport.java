package br.com.alura.case_tecnico.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseNpsReport {

    private String courseCode;
    private String courseName;
    private Long totalEnrollments;
    private Long promoters;
    private Long detractors;
    private Long totalFeedbacks;
    private Integer npsScore;

    public CourseNpsReport(String courseCode, String courseName, Long totalEnrollments, Long promoters, Long detractors, Long totalFeedbacks) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.totalEnrollments = totalEnrollments;
        this.promoters = promoters;
        this.detractors = detractors;
        this.totalFeedbacks = totalFeedbacks;
        this.npsScore = this.calculateNps(promoters, detractors, totalFeedbacks);
    }

    public CourseNpsReport() {
    }

    private int calculateNps(long promoters, long detractors, long totalFeedbacks) {
        if (totalFeedbacks == 0) {
            return 0;
        }
        return (int) (((double) (promoters - detractors) / totalFeedbacks) * 100);
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

    public Long getTotalEnrollments() {
        return totalEnrollments;
    }

    public void setTotalEnrollments(Long totalEnrollments) {
        this.totalEnrollments = totalEnrollments;
    }

    public Long getPromoters() {
        return promoters;
    }

    public void setPromoters(Long promoters) {
        this.promoters = promoters;
    }

    public Long getDetractors() {
        return detractors;
    }

    public void setDetractors(Long detractors) {
        this.detractors = detractors;
    }

    public Long getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(Long totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }

    public Integer getNpsScore() {
        return npsScore;
    }

    public void setNpsScore(Integer npsScore) {
        this.npsScore = npsScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseNpsReport that = (CourseNpsReport) o;
        return Objects.equals(totalEnrollments, that.totalEnrollments) &&
                Objects.equals(promoters, that.promoters) &&
                Objects.equals(detractors, that.detractors) &&
                Objects.equals(totalFeedbacks, that.totalFeedbacks)
                && Objects.equals(npsScore, that.npsScore) &&
                Objects.equals(courseCode, that.courseCode) &&
                Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseName, totalEnrollments, promoters, detractors, totalFeedbacks, npsScore);
    }

}
