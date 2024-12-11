package com.employee.performance.service;

import com.employee.performance.dto.EmployeeSelfReviewDTO;
import com.employee.performance.dto.ManagerReviewDTO;
import com.employee.performance.entity.EmployeeSelfReview;
import com.employee.performance.entity.ManagerReview;
import com.employee.performance.entity.PerformanceSummary;

public interface PerformanceSummaryService {
	
	PerformanceSummary generatePerformanceSummary(Long employeeId);

	int calculateSelfReviewScore(EmployeeSelfReview selfReview);

	double calculatePerformanceScore(int selfReviewScore, int managerRating, double managerWeight);

	EmployeeSelfReview saveSelfReview(EmployeeSelfReviewDTO selfReviewDTO);

	ManagerReview saveManagerReview(ManagerReviewDTO managerReviewDTO);
}
