package com.coverwell.crm.controller;

import com.coverwell.crm.service.EmployeePerformanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee-performance")
@CrossOrigin(origins = "*")
public class EmployeePerformanceController {

    private final EmployeePerformanceService performanceService;

    public EmployeePerformanceController(
            EmployeePerformanceService performanceService) {

        this.performanceService = performanceService;
    }

    @GetMapping
    public List<Map<String, Object>> getEmployeePerformance() {

        return performanceService.getEmployeePerformance();
    }
}