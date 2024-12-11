package com.employee.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.performance.dto.EmployeeSelfReviewDTO;
import com.employee.performance.dto.ManagerReviewDTO;
import com.employee.performance.entity.EmployeeSelfReview;
import com.employee.performance.entity.ManagerReview;
import com.employee.performance.entity.PerformanceSummary;
import com.employee.performance.service.PerformanceSummaryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/performance")
public class PerformanceSummaryController {

    @Autowired
    private PerformanceSummaryService performanceSummaryService;
    
    @Operation(summary = "Save Self-review", description = "Save employee self-review")
	@PostMapping("/self-review")
	public ResponseEntity<EmployeeSelfReview> submitSelfReview(@RequestBody EmployeeSelfReviewDTO selfReviewDTO) {
		return ResponseEntity.ok(performanceSummaryService.saveSelfReview(selfReviewDTO));
	}


    @Operation(summary = "Save Manager-review", description = "Save manager review")
    @PostMapping("/manager-review")
    public ResponseEntity<ManagerReview> submitManagerReview(@RequestBody ManagerReviewDTO managerReviewDTO) {
        return ResponseEntity.ok(performanceSummaryService.saveManagerReview(managerReviewDTO));
    }

    @Operation(summary = "Get Performance Summary By Employee ID", description = "Get Performance Summary By Employee ID")
    @GetMapping("/{employeeId}")
    public PerformanceSummary getPerformanceSummary(@PathVariable Long employeeId) {
        return performanceSummaryService.generatePerformanceSummary(employeeId);
    }
}

