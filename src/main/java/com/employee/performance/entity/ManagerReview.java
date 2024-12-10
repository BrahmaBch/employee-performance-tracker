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
@Table(schema = "demo", name = "manager_reviews")
public class ManagerReview {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerReviewId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String reviewText;
    private int rating;  // Rating between 1 and 5
    private LocalDateTime submittedAt;
    
	public Long getManagerReviewId() {
		return managerReviewId;
	}
	public void setManagerReviewId(Long managerReviewId) {
		this.managerReviewId = managerReviewId;
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}
	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}
}
