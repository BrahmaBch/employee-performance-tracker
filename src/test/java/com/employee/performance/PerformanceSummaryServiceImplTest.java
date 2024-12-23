package com.employee.performance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
import com.employee.performance.service.impl.PerformanceSummaryServiceImpl;

@ExtendWith(MockitoExtension.class) // Extends to use Mockito in JUnit 5
public class PerformanceSummaryServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeSelfReviewRepository selfReviewRepository;

    @Mock
    private ManagerReviewRepository managerReviewRepository;

    @Mock
    private PerformanceSummaryRepository performanceSummaryRepository;

    @InjectMocks
    private PerformanceSummaryServiceImpl performanceSummaryService;

    private Employee employee;
    private EmployeeSelfReview selfReview;
    private ManagerReview managerReview;
    private EmployeeSelfReviewDTO selfReviewDTO;
    private ManagerReviewDTO managerReviewDTO;

    @BeforeEach
    public void setUp() {
        employee = new Employee(1L, "John Doe", "Developer");
        selfReview = new EmployeeSelfReview();
        selfReview.setEmployee(employee);
        selfReview.setReviewText("This is a self-review");
        
        managerReview = new ManagerReview();
        managerReview.setEmployee(employee);
        managerReview.setReviewText("This is a manager review");
        managerReview.setRating(4);
        managerReview.setManagerWeight(0.5);

        selfReviewDTO = new EmployeeSelfReviewDTO();
        selfReviewDTO.setEmployeeId(1L);
        selfReviewDTO.setReviewText("This is a self-review");
        
        managerReviewDTO = new ManagerReviewDTO();
        managerReviewDTO.setEmployeeId(1L);
        managerReviewDTO.setReviewText("This is a manager review");
        managerReviewDTO.setRating(4);
        managerReviewDTO.setManagerWeight(0.5);
    }

    @Test
    public void testSaveSelfReview_EmployeeNotFound() {
        when(employeeRepository.findById(selfReviewDTO.getEmployeeId())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> performanceSummaryService.saveSelfReview(selfReviewDTO));
        verify(employeeRepository, times(1)).findById(selfReviewDTO.getEmployeeId());
    }

    @Test
    public void testSaveSelfReview_Success() {
        when(employeeRepository.findById(selfReviewDTO.getEmployeeId())).thenReturn(Optional.of(employee));
        when(selfReviewRepository.save(any(EmployeeSelfReview.class))).thenReturn(selfReview);
        EmployeeSelfReview result = performanceSummaryService.saveSelfReview(selfReviewDTO);
        assertNotNull(result);
        assertEquals("This is a self-review", result.getReviewText());
        verify(employeeRepository, times(1)).findById(selfReviewDTO.getEmployeeId());
        verify(selfReviewRepository, times(1)).save(any(EmployeeSelfReview.class));
    }

    @Test
    public void testSaveManagerReview_EmployeeNotFound() {
        when(employeeRepository.findById(managerReviewDTO.getEmployeeId())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> performanceSummaryService.saveManagerReview(managerReviewDTO));
        verify(employeeRepository, times(1)).findById(managerReviewDTO.getEmployeeId());
    }

    @Test
    public void testSaveManagerReview_Success() {
        when(employeeRepository.findById(managerReviewDTO.getEmployeeId())).thenReturn(Optional.of(employee));
        when(managerReviewRepository.save(any(ManagerReview.class))).thenReturn(managerReview);
        ManagerReview result = performanceSummaryService.saveManagerReview(managerReviewDTO);
        assertNotNull(result);
        assertEquals(4, result.getRating());
        assertEquals("This is a manager review", result.getReviewText());
        verify(employeeRepository, times(1)).findById(managerReviewDTO.getEmployeeId());
        verify(managerReviewRepository, times(1)).save(any(ManagerReview.class));
    }

    @Test
    public void testGeneratePerformanceSummary_Success() {
        when(selfReviewRepository.findByEmployeeId(1L)).thenReturn(selfReview);
        when(managerReviewRepository.findByEmployeeId(1L)).thenReturn(managerReview);
        when(performanceSummaryRepository.save(any(PerformanceSummary.class))).thenReturn(new PerformanceSummary());
        PerformanceSummary summary = performanceSummaryService.generatePerformanceSummary(1L);
        assertNotNull(summary);
        assertEquals(1.5, summary.getPerformanceScore(), 0.1);
        verify(selfReviewRepository, times(1)).findByEmployeeId(1L);
        verify(managerReviewRepository, times(1)).findByEmployeeId(1L);
        verify(performanceSummaryRepository, times(1)).save(any(PerformanceSummary.class));
    }

    @Test
    public void testCalculateSelfReviewScore_Success() {
        int score = performanceSummaryService.calculateSelfReviewScore(selfReview);
        assertEquals(1, score);
    }

    @Test
    public void testCalculatePerformanceScore_Success() {
        double score = performanceSummaryService.calculatePerformanceScore(3, 4, 0.5);
        assertEquals(2.5, score, 0.1);
    }
}

