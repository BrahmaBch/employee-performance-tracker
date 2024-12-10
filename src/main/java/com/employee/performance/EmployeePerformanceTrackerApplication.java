package com.employee.performance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	    info = @Info(
		        title = "Employee Performance API",
		        version = "1.0",
		        description = "Task is to create a REST API that provides a performance summary for\n"
		        		+ "an individual employee")
	)
public class EmployeePerformanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePerformanceTrackerApplication.class, args);
	}

}
