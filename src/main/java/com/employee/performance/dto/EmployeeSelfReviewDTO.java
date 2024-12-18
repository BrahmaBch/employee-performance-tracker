package com.employee.performance.dto;

import java.time.LocalDateTime;

public class EmployeeSelfReviewDTO {

	private Long selfReviewId;
    private Long employeeId;
    private String reviewText;
    private LocalDateTime submittedAt;
    
	public Long getSelfReviewId() {
		return selfReviewId;
	}
	public void setSelfReviewId(Long selfReviewId) {
		this.selfReviewId = selfReviewId;
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
	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}
	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}
    
}
