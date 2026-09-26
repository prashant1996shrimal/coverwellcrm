package com.coverwell.crm.controller;

import com.coverwell.crm.dto.EmployeeCallStats;
import com.coverwell.crm.entity.CallLog;
import com.coverwell.crm.service.CallLogService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/call-logs")
@CrossOrigin(origins = "*")
public class CallLogController {

    private final CallLogService callLogService;

    public CallLogController(CallLogService callLogService) {
        this.callLogService = callLogService;
    }

    

    // GET ALL CALLS
    @GetMapping
    public ResponseEntity<List<CallLog>> getAllCalls() {

        return ResponseEntity.ok(
                callLogService.getAllCalls()
        );
    }


    // GET CALL BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CallLog> getCallById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                callLogService.getCallById(id)
        );
    }


    // GET CALLS FOR EMPLOYEE
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<CallLog>> getCallsByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                callLogService.getCallsByEmployee(employeeId)
        );
    }


    // CREATE CALL
    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<CallLog> createCall(
            @PathVariable Long employeeId,
            @RequestBody CallLog callLog) {

        CallLog savedCall =
                callLogService.createCall(
                        employeeId,
                        callLog
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCall);
    }


    // ==========================================
    // EMPLOYEE CALL STATISTICS
    // ==========================================

    // TOTAL CALLS
    @GetMapping("/employee/{employeeId}/count")
    public ResponseEntity<Long> getEmployeeCallCount(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                callLogService.getEmployeeCallCount(employeeId)
        );
    }


    // OUTGOING CALLS
    @GetMapping("/employee/{employeeId}/outgoing")
    public ResponseEntity<Long> getEmployeeOutgoingCalls(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                callLogService.getEmployeeOutgoingCallCount(employeeId)
        );
    }


    // INCOMING CALLS
    @GetMapping("/employee/{employeeId}/incoming")
    public ResponseEntity<Long> getEmployeeIncomingCalls(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                callLogService.getEmployeeIncomingCallCount(employeeId)
        );
    }
    @GetMapping("/employee/{employeeId}/stats")
public ResponseEntity<EmployeeCallStats> getEmployeeCallStats(
        @PathVariable Long employeeId) {

    return ResponseEntity.ok(
            callLogService.getEmployeeCallStats(employeeId)
    );
}
// GET CALL STATISTICS FOR ALL EMPLOYEES
@GetMapping("/stats/employees")
public ResponseEntity<List<EmployeeCallStats>> getAllEmployeeCallStats() {

    return ResponseEntity.ok(
            callLogService.getAllEmployeeCallStats()
    );
}

@GetMapping("/stats/employees/date")
public ResponseEntity<List<EmployeeCallStats>> getEmployeeCallStatsByDate(
        @RequestParam String from,
        @RequestParam String to) {

    LocalDate startDate =
            LocalDate.parse(from);

    LocalDate endDate =
            LocalDate.parse(to);

    LocalDateTime start =
            startDate.atStartOfDay();

    LocalDateTime end =
            endDate.plusDays(1).atStartOfDay().minusNanos(1);

    return ResponseEntity.ok(
            callLogService.getEmployeeCallStatsByDate(
                    start,
                    end
            )
    );
}
}