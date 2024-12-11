package com.employee.performance.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

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

public class PerformanceSummaryServiceImplTest {

    @Mock
    private EmployeeSelfReviewRepository selfReviewRepository;

    @Mock
    private ManagerReviewRepository managerReviewRepository;

    @Mock
    private PerformanceSummaryRepository performanceSummaryRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private PerformanceSummaryServiceImpl performanceSummaryService;

    private Employee employee;
    private EmployeeSelfReview employeeSelfReview;
    private ManagerReview managerReview;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup mock employee
        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("John");

        // Setup mock EmployeeSelfReview
        employeeSelfReview = new EmployeeSelfReview();
        employeeSelfReview.setEmployee(employee);
        employeeSelfReview.setReviewText("Great job overall!");

        // Setup mock ManagerReview
        managerReview = new ManagerReview();
        managerReview.setEmployee(employee);
        managerReview.setRating(4);
        managerReview.setManagerWeight(0.7);
    }

    @Test
    void testSaveSelfReview_Success() {
        EmployeeSelfReviewDTO selfReviewDTO = new EmployeeSelfReviewDTO();
        selfReviewDTO.setEmployeeId(1L);
        selfReviewDTO.setReviewText("Great job overall!");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(selfReviewRepository.save(any(EmployeeSelfReview.class))).thenReturn(employeeSelfReview);

        EmployeeSelfReview savedSelfReview = performanceSummaryService.saveSelfReview(selfReviewDTO);

        assertNotNull(savedSelfReview);
        assertEquals("Great job overall!", savedSelfReview.getReviewText());
        verify(employeeRepository, times(1)).findById(1L);
        verify(selfReviewRepository, times(1)).save(any(EmployeeSelfReview.class));
    }

    @Test
    void testSaveSelfReview_EmployeeNotFound() {
        EmployeeSelfReviewDTO selfReviewDTO = new EmployeeSelfReviewDTO();
        selfReviewDTO.setEmployeeId(1L);
        selfReviewDTO.setReviewText("Great job overall!");

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            performanceSummaryService.saveSelfReview(selfReviewDTO);
        });

        assertEquals("Employee with ID 1 does not exist.", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveManagerReview_Success() {
        ManagerReviewDTO managerReviewDTO = new ManagerReviewDTO();
        managerReviewDTO.setEmployeeId(1L);
        managerReviewDTO.setReviewText("Good performance.");
        managerReviewDTO.setRating(4);
        managerReviewDTO.setManagerWeight(0.75);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(managerReviewRepository.save(any(ManagerReview.class))).thenReturn(managerReview);

        ManagerReview savedManagerReview = performanceSummaryService.saveManagerReview(managerReviewDTO);

        assertNotNull(savedManagerReview);
      //  assertEquals("Good performance.", savedManagerReview.getReviewText());
        assertEquals(4, savedManagerReview.getRating());
        
        verify(employeeRepository, times(1)).findById(1L);
        verify(managerReviewRepository, times(1)).save(any(ManagerReview.class));
    }


    @Test
    void testSaveManagerReview_EmployeeNotFound() {
        ManagerReviewDTO managerReviewDTO = new ManagerReviewDTO();
        managerReviewDTO.setEmployeeId(1L);
        managerReviewDTO.setReviewText("Good performance.");
        managerReviewDTO.setRating(4);
        managerReviewDTO.setManagerWeight(0.75);

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            performanceSummaryService.saveManagerReview(managerReviewDTO);
        });

        assertEquals("Employee with ID 1 does not exist.", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGeneratePerformanceSummary() {
        when(selfReviewRepository.findByEmployeeId(1L)).thenReturn(employeeSelfReview);
        when(managerReviewRepository.findByEmployeeId(1L)).thenReturn(managerReview);
        when(performanceSummaryRepository.save(any(PerformanceSummary.class))).thenReturn(new PerformanceSummary());

        PerformanceSummary summary = performanceSummaryService.generatePerformanceSummary(1L);

        assertNotNull(summary);
        verify(selfReviewRepository, times(1)).findByEmployeeId(1L);
        verify(managerReviewRepository, times(1)).findByEmployeeId(1L);
        verify(performanceSummaryRepository, times(1)).save(any(PerformanceSummary.class));
    }

    @Test
    void testCalculateSelfReviewScore_ShortReview() {
        employeeSelfReview.setReviewText("Short review");

        int score = performanceSummaryService.calculateSelfReviewScore(employeeSelfReview);

        assertEquals(1, score);
    }

    @Test
    void testCalculateSelfReviewScore_MediumReview() {
        employeeSelfReview.setReviewText("This is a calculation of the medium length review of text input.");

        int score = performanceSummaryService.calculateSelfReviewScore(employeeSelfReview);

        assertEquals(3, score);
    }

    @Test
    void testCalculateSelfReviewScore_LongReview() {
        employeeSelfReview.setReviewText("This is a long review that contains a lot of details about the employee's performance, strengths, and areas for improvement.");

        int score = performanceSummaryService.calculateSelfReviewScore(employeeSelfReview);

        assertEquals(3, score);
    }

    @Test
    void testCalculatePerformanceScore() {
        int selfReviewScore = 3;
        int managerRating = 4;
        double managerWeight = 0.8;

        double performanceScore = performanceSummaryService.calculatePerformanceScore(selfReviewScore, managerRating, managerWeight);

        assertEquals(3.1, performanceScore, 0.1);
    }
}

