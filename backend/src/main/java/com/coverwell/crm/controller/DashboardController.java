package com.coverwell.crm.controller;

import com.coverwell.crm.dto.DashboardStats;
import com.coverwell.crm.repository.CallLogRepository;

import com.coverwell.crm.repository.EmployeeRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final EmployeeRepository employeeRepository;
    private final CallLogRepository callLogRepository;

    public DashboardController(
            EmployeeRepository employeeRepository,
            CallLogRepository callLogRepository) {

        this.employeeRepository = employeeRepository;
        this.callLogRepository = callLogRepository;
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStats> getDashboardStats() {

        long totalEmployees =
                employeeRepository.count();

        long totalCalls =
                callLogRepository.count();

        long totalOutgoingCalls =
                callLogRepository
                        .countByCallType("OUTGOING");

        long totalIncomingCalls =
                callLogRepository
                        .countByCallType("INCOMING");

        long missedCalls =
                callLogRepository
                        .countByCallType("MISSED");

        DashboardStats stats =
                new DashboardStats(
                        totalEmployees,
                        totalCalls,
                        totalOutgoingCalls,
                        totalIncomingCalls,
                        missedCalls
                );

        return ResponseEntity.ok(stats);
    }
}