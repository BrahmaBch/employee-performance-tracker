package com.employee.performance.service;

import com.employee.performance.entity.EmployeeSelfReview;
import com.employee.performance.entity.PerformanceSummary;

public interface PerformanceSummaryService {
	
	PerformanceSummary generatePerformanceSummary(Long employeeId);

	int calculateSelfReviewScore(EmployeeSelfReview selfReview);

	double calculatePerformanceScore(int selfReviewScore, int managerReviewScore);
}
