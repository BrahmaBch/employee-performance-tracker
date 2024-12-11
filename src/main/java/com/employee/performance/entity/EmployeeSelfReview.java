package com.employee.performance.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "demo", name = "employee_self_reviews")
public class EmployeeSelfReview {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selfReviewId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private String reviewText;
    private LocalDateTime submittedAt;
	public Long getSelfReviewId() {
		return selfReviewId;
	}
	public void setSelfReviewId(Long selfReviewId) {
		this.selfReviewId = selfReviewId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
