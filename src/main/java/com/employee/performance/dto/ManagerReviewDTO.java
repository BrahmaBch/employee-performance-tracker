package com.employee.performance.dto;

import java.time.LocalDateTime;

public class ManagerReviewDTO {

	private Long managerReviewId;
    private Long employeeId;
    private String reviewText;
    private int rating;
    private double managerWeight;
    private LocalDateTime submittedAt;

    public Long getManagerReviewId() {
        return managerReviewId;
    }

    public void setManagerReviewId(Long managerReviewId) {
        this.managerReviewId = managerReviewId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getManagerWeight() {
        return managerWeight;
    }

    public void setManagerWeight(double managerWeight) {
        this.managerWeight = managerWeight;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
