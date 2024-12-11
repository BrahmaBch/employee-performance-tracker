package com.employee.performance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.performance.service.impl.PerformanceSummaryServiceImpl;

@SpringBootTest(classes = PerformanceSummaryServiceImpl.class)
@ExtendWith(MockitoExtension.class)
class EmployeePerformanceTrackerApplicationTests {

	@Test
	void contextLoads() {
	}

}
