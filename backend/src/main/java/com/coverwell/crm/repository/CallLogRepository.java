package com.coverwell.crm.repository;

import com.coverwell.crm.entity.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallLogRepository
        extends JpaRepository<CallLog, Long> {

    List<CallLog> findByEmployeeId(Long employeeId);

    long countByEmployeeId(Long employeeId);

    long countByEmployeeIdAndCallType(
            Long employeeId,
            String callType
    );

    long countByCallType(String callType);
    long countByEmployeeIdAndCallStartBetween(
        Long employeeId,
        java.time.LocalDateTime start,
        java.time.LocalDateTime end
);

long countByEmployeeIdAndCallStartBetweenAndCallType(
        Long employeeId,
        java.time.LocalDateTime start,
        java.time.LocalDateTime end,
        String callType
);
}