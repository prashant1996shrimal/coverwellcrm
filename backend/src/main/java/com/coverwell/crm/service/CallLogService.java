package com.coverwell.crm.service;

import com.coverwell.crm.entity.CallLog;
import com.coverwell.crm.entity.Employee;
import com.coverwell.crm.repository.CallLogRepository;
import com.coverwell.crm.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import com.coverwell.crm.dto.EmployeeCallStats;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CallLogService {

    private final CallLogRepository callLogRepository;
    private final EmployeeRepository employeeRepository;

    public CallLogService(
            CallLogRepository callLogRepository,
            EmployeeRepository employeeRepository) {

        this.callLogRepository = callLogRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeCallStats> getAllEmployeeCallStats() {

        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeCallStats> stats = new ArrayList<>();

        for (Employee employee : employees) {

            Long employeeId = employee.getId();

            long totalCalls = callLogRepository.countByEmployeeId(employeeId);

            long outgoingCalls = callLogRepository.countByEmployeeIdAndCallType(
                    employeeId,
                    "OUTGOING");

            long incomingCalls = callLogRepository.countByEmployeeIdAndCallType(
                    employeeId,
                    "INCOMING");
                    long missedCalls =
        callLogRepository.countByEmployeeIdAndCallType(
                employee.getId(),
                "MISSED"
        );

            stats.add(
                    new EmployeeCallStats(
                            employeeId,
                            employee.getName(),
                            totalCalls,
                            outgoingCalls,
                            incomingCalls,
                            missedCalls));
        }

        return stats;
    }

    public EmployeeCallStats getEmployeeCallStats(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException(
                        "Employee not found with id: " + employeeId));

        long totalCalls = callLogRepository.countByEmployeeId(employeeId);

        long outgoingCalls = callLogRepository.countByEmployeeIdAndCallType(
                employeeId,
                "OUTGOING");

        long incomingCalls = callLogRepository.countByEmployeeIdAndCallType(
                employeeId,
                "INCOMING");

                long missedCalls =
        callLogRepository.countByEmployeeIdAndCallType(
                employee.getId(),
                "MISSED"
        );

        return new EmployeeCallStats(
                employee.getId(),
                employee.getName(),
                totalCalls,
                outgoingCalls,
                incomingCalls,
            missedCalls);
    }

    // Get all calls
    public List<CallLog> getAllCalls() {

        return callLogRepository.findAll();
    }

    // Get calls for an employee
    public List<CallLog> getCallsByEmployee(Long employeeId) {

        return callLogRepository.findByEmployeeId(employeeId);
    }

    // Create call log
    public CallLog createCall(
            Long employeeId,
            CallLog callLog) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException(
                        "Employee not found with id: " + employeeId));

        callLog.setEmployee(employee);

        return callLogRepository.save(callLog);
    }

    // Get call by ID
    public CallLog getCallById(Long id) {

        return callLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Call not found with id: " + id));
    }

    // Get total calls for employee
    public long getEmployeeCallCount(Long employeeId) {

        return callLogRepository.countByEmployeeId(employeeId);
    }

    // Get outgoing calls for employee
    public long getEmployeeOutgoingCallCount(Long employeeId) {

        return callLogRepository.countByEmployeeIdAndCallType(
                employeeId,
                "OUTGOING");
    }

    // Get incoming calls for employee
    public long getEmployeeIncomingCallCount(Long employeeId) {

        return callLogRepository.countByEmployeeIdAndCallType(
                employeeId,
                "INCOMING");
    }

    public List<EmployeeCallStats> getEmployeeCallStatsByDate(
        LocalDateTime start,
        LocalDateTime end) {

    List<Employee> employees =
            employeeRepository.findAll();

    List<EmployeeCallStats> stats =
            new ArrayList<>();

    for (Employee employee : employees) {

        Long employeeId = employee.getId();

        long totalCalls =
                callLogRepository
                        .countByEmployeeIdAndCallStartBetween(
                                employeeId,
                                start,
                                end
                        );

        long outgoingCalls =
                callLogRepository
                        .countByEmployeeIdAndCallStartBetweenAndCallType(
                                employeeId,
                                start,
                                end,
                                "OUTGOING"
                        );

        long incomingCalls =
                callLogRepository
                        .countByEmployeeIdAndCallStartBetweenAndCallType(
                                employeeId,
                                start,
                                end,
                                "INCOMING"
                        );

        long missedCalls =
                callLogRepository
                        .countByEmployeeIdAndCallStartBetweenAndCallType(
                                employeeId,
                                start,
                                end,
                                "MISSED"
                        );

        stats.add(
                new EmployeeCallStats(
                        employeeId,
                        employee.getName(),
                        totalCalls,
                        outgoingCalls,
                        incomingCalls,
                        missedCalls
                )
        );
    }

    return stats;
}
}