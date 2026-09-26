package com.coverwell.crm.service;

import com.coverwell.crm.entity.CallLog;
import com.coverwell.crm.repository.CallLogRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final CallLogRepository callLogRepository;

    public DashboardService(CallLogRepository callLogRepository) {
        this.callLogRepository = callLogRepository;
    }

    public Map<String, Object> getStats() {

        List<CallLog> calls = callLogRepository.findAll();

        long totalCalls = calls.size();

        long outgoingCalls = calls.stream()
                .filter(call -> "OUTGOING".equalsIgnoreCase(call.getCallType()))
                .count();

        long incomingCalls = calls.stream()
                .filter(call -> "INCOMING".equalsIgnoreCase(call.getCallType()))
                .count();

        long missedCalls = calls.stream()
                .filter(call -> "MISSED".equalsIgnoreCase(call.getCallType()))
                .count();

        long totalDuration = calls.stream()
                .filter(call -> call.getDurationSeconds() != null)
                .mapToLong(CallLog::getDurationSeconds)
                .sum();

        Map<String, Object> stats = new HashMap<>();

        stats.put("totalCalls", totalCalls);
        stats.put("outgoingCalls", outgoingCalls);
        stats.put("incomingCalls", incomingCalls);
        stats.put("missedCalls", missedCalls);
        stats.put("totalDurationSeconds", totalDuration);

        return stats;
    }
}