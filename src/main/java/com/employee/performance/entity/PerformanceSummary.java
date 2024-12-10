package com.employee.performance.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "demo", name="performance_summary")
public class PerformanceSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long summaryId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private int selfReviewScore;
    private int managerReviewScore;
    private double performanceScore;
    private LocalDateTime generatedAt;
	public Long getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(Long summaryId) {
		this.summaryId = summaryId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getSelfReviewScore() {
		return selfReviewScore;
	}
	public void setSelfReviewScore(int selfReviewScore) {
		this.selfReviewScore = selfReviewScore;
	}
	public int getManagerReviewScore() {
		return managerReviewScore;
	}
	public void setManagerReviewScore(int managerReviewScore) {
		this.managerReviewScore = managerReviewScore;
	}
	public double getPerformanceScore() {
		return performanceScore;
	}
	public void setPerformanceScore(double performanceScore) {
		this.performanceScore = performanceScore;
	}
	public LocalDateTime getGeneratedAt() {
		return generatedAt;
	}
	public void setGeneratedAt(LocalDateTime generatedAt) {
		this.generatedAt = generatedAt;
	}
    
}

