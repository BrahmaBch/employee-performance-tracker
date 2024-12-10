package com.employee.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.performance.entity.PerformanceSummary;

@Repository
public interface PerformanceSummaryRepository extends JpaRepository<PerformanceSummary, Long> {

}
