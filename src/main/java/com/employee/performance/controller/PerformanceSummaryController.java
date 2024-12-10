package com.employee.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.performance.entity.PerformanceSummary;
import com.employee.performance.service.PerformanceSummaryService;

@RestController
@RequestMapping("/api/performance")
public class PerformanceSummaryController {

    @Autowired
    private PerformanceSummaryService performanceSummaryService;

    @GetMapping("/{employeeId}")
    public PerformanceSummary getPerformanceSummary(@PathVariable Long employeeId) {
        return performanceSummaryService.generatePerformanceSummary(employeeId);
    }
}

