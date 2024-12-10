package com.employee.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.performance.entity.EmployeeSelfReview;

@Repository
public interface EmployeeSelfReviewRepository extends JpaRepository<EmployeeSelfReview, Long> {
	
	@Query(value = "SELECT * FROM demo.employee_self_reviews esr WHERE esr.employee_id = :employeeId", nativeQuery = true)
    EmployeeSelfReview findByEmployeeId(@Param("employeeId") Long employeeId);
	
}
