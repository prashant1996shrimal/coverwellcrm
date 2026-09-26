package com.coverwell.crm.repository;

import com.coverwell.crm.entity.Recording;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordingRepository extends JpaRepository<Recording, Long> {

    List<Recording> findByEmployeeId(Long employeeId);

    List<Recording> findByCallLogId(Long callLogId);
}