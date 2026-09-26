package com.coverwell.crm.service;

import com.coverwell.crm.entity.CallLog;
import com.coverwell.crm.entity.Employee;
import com.coverwell.crm.repository.CallLogRepository;
import com.coverwell.crm.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeePerformanceService {

    private final EmployeeRepository employeeRepository;
    private final CallLogRepository callLogRepository;

    public EmployeePerformanceService(
            EmployeeRepository employeeRepository,
            CallLogRepository callLogRepository) {

        this.employeeRepository = employeeRepository;
        this.callLogRepository = callLogRepository;
    }

    public List<Map<String, Object>> getEmployeePerformance() {

        List<Employee> employees = employeeRepository.findAll();
        List<CallLog> calls = callLogRepository.findAll();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Employee employee : employees) {

            long callCount = calls.stream()
                    .filter(call ->
                            call.getEmployee() != null &&
                            call.getEmployee().getId()
                                    .equals(employee.getId()))
                    .count();

            long totalDuration = calls.stream()
                    .filter(call ->
                            call.getEmployee() != null &&
                            call.getEmployee().getId()
                                    .equals(employee.getId()))
                    .filter(call -> call.getDurationSeconds() != null)
                    .mapToLong(CallLog::getDurationSeconds)
                    .sum();

            Map<String, Object> data = new HashMap<>();

            data.put("id", employee.getId());
            data.put("name", employee.getName());
            data.put("email", employee.getEmail());
            data.put("phone", employee.getPhone());
            data.put("role", employee.getRole());
            data.put("status", employee.getStatus());
            data.put("callCount", callCount);
            data.put("totalDurationSeconds", totalDuration);

            result.add(data);
        }

        return result;
    }
}