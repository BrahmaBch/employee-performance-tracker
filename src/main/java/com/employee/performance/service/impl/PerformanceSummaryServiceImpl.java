package com.employee.performance.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.performance.dto.EmployeeSelfReviewDTO;
import com.employee.performance.dto.ManagerReviewDTO;
import com.employee.performance.entity.Employee;
import com.employee.performance.entity.EmployeeSelfReview;
import com.employee.performance.entity.ManagerReview;
import com.employee.performance.entity.PerformanceSummary;
import com.employee.performance.repository.EmployeeRepository;
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
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Override
	public EmployeeSelfReview saveSelfReview(EmployeeSelfReviewDTO selfReviewDTO) {
	    Optional<Employee> existingEmployee = employeeRepository.findById(selfReviewDTO.getEmployeeId());
	    
	    if (!existingEmployee.isPresent()) {
	        throw new IllegalArgumentException("Employee with ID " + selfReviewDTO.getEmployeeId() + " does not exist.");
	    }
	    EmployeeSelfReview selfReview = new EmployeeSelfReview();
	    selfReview.setEmployee(existingEmployee.get());
	    selfReview.setReviewText(selfReviewDTO.getReviewText());
	    selfReview.setSubmittedAt(LocalDateTime.now());
	    
	    return selfReviewRepository.save(selfReview);
	}

	
	@Override
	public ManagerReview saveManagerReview(ManagerReviewDTO managerReviewDTO) {
	    Optional<Employee> existingEmployee = employeeRepository.findById(managerReviewDTO.getEmployeeId());
	    
	    if (!existingEmployee.isPresent()) {
	        throw new IllegalArgumentException("Employee with ID " + managerReviewDTO.getEmployeeId() + " does not exist.");
	    }

	    ManagerReview managerReview = new ManagerReview();
	    managerReview.setEmployee(existingEmployee.get());
	    managerReview.setReviewText(managerReviewDTO.getReviewText());
	    managerReview.setRating(managerReviewDTO.getRating());
	    managerReview.setManagerWeight(managerReviewDTO.getManagerWeight());
	    managerReview.setSubmittedAt(LocalDateTime.now());
	    
	    ManagerReview savedManagerReview = managerReviewRepository.save(managerReview);
	    //System.out.println("Saved Manager Review Text: " + savedManagerReview.getReviewText());
	    
	    return savedManagerReview;
	}


	@Override
	public PerformanceSummary generatePerformanceSummary(Long employeeId) {
		EmployeeSelfReview employeeSelfReview = selfReviewRepository.findByEmployeeId(employeeId);
		ManagerReview managerReview = managerReviewRepository.findByEmployeeId(employeeId);

		int selfReviewScore = calculateSelfReviewScore(employeeSelfReview);
		//System.out.println("selfReviewScore: "+selfReviewScore);

		int managerRating = managerReview.getRating();
		//System.out.println("managerRating: "+managerRating);
		
		double managerWeight = managerReview.getManagerWeight();

		double performanceScore = calculatePerformanceScore(selfReviewScore, managerRating, managerWeight);

		PerformanceSummary summary = new PerformanceSummary();
		summary.setEmployee(employeeSelfReview.getEmployee());
		summary.setSelfReviewScore(selfReviewScore);
		summary.setManagerReviewScore(managerRating);
		summary.setPerformanceScore(performanceScore);
		summary.setGeneratedAt(LocalDateTime.now());

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
	public double calculatePerformanceScore(int selfReviewScore, int managerRating, double managerWeight) {
		double performanceScore = (selfReviewScore + (managerRating * managerWeight)) / 2;
		System.out.println("performanceScore: "+performanceScore);
		return performanceScore;

	}

	

}
