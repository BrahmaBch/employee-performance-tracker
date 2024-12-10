package com.employee.performance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.performance.entity.EmployeeSelfReview;
import com.employee.performance.entity.ManagerReview;
import com.employee.performance.entity.PerformanceSummary;
import com.employee.performance.repository.EmployeeSelfReviewRepository;
import com.employee.performance.repository.ManagerReviewRepository;
import com.employee.performance.repository.PerformanceSummaryRepository;
import com.employee.performance.service.PerformanceSummaryService;

@Service
public class PerformanceSummaryServiceImpl implements PerformanceSummaryService {

	@Autowired
	private EmployeeSelfReviewRepository selfReviewRepository;

	@Autowired
	private ManagerReviewRepository managerReviewRepository;

	@Autowired
	private PerformanceSummaryRepository performanceSummaryRepository;

	@Override
	public PerformanceSummary generatePerformanceSummary(Long employeeId) {
		// Retrieve self-review and manager review based on employee ID
		EmployeeSelfReview employeeSelfReview = selfReviewRepository.findByEmployeeId(employeeId);
		ManagerReview managerReview = managerReviewRepository.findByEmployeeId(employeeId);

		// Calculate self-review score based on the review length
		int selfReviewScore = calculateSelfReviewScore(employeeSelfReview);
		System.out.println("selfReviewScore: "+selfReviewScore);

		// Extract the manager's rating (ensure it's between 1 and 5)
		int managerRating = managerReview.getRating();
		System.out.println("managerRating: "+managerRating);

		// Calculate the derived performance score
		double performanceScore = calculatePerformanceScore(selfReviewScore, managerRating);

		// Create and save the performance summary
		PerformanceSummary summary = new PerformanceSummary();
		summary.setEmployee(employeeSelfReview.getEmployee());
		summary.setSelfReviewScore(selfReviewScore);
		summary.setManagerReviewScore(managerRating);
		summary.setPerformanceScore(performanceScore);

		performanceSummaryRepository.save(summary);

		return summary;

	}

	@Override
	public int calculateSelfReviewScore(EmployeeSelfReview selfReview) {
		String reviewText = selfReview.getReviewText();
		int reviewLength = reviewText.length();

		if (reviewLength <= 50) {
			return 1; // Short review - low score
		} else if (reviewLength <= 150) {
			return 3; // Medium review - moderate score
		} else {
			return 5; // Long review - high score
		}

	}

	@Override
	public double calculatePerformanceScore(int selfReviewScore, int managerRating) {
		// manager rating (e.g: 2)
		double managerWeight = 2;
		double performanceScore = (selfReviewScore + (managerRating * managerWeight)) / 2;
		System.out.println("performanceScore: "+performanceScore);
		// Calculate performance score using the given formula
		return performanceScore;

	}

}
