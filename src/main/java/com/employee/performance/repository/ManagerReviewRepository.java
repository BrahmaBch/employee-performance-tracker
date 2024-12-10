package com.employee.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.performance.entity.ManagerReview;

@Repository
public interface ManagerReviewRepository extends JpaRepository<ManagerReview, Long> {

	@Query("SELECT mr FROM ManagerReview mr WHERE mr.employee.employee_id = :employeeId")
    ManagerReview findByEmployeeId(@Param("employeeId") Long employeeId);

}
