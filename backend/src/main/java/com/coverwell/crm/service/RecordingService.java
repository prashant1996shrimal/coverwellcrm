package com.coverwell.crm.service;

import com.coverwell.crm.entity.Recording;
import com.coverwell.crm.repository.RecordingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class RecordingService {

    private final RecordingRepository recordingRepository;

    private final Path recordingDirectory =
            Paths.get("D:/Coverwell_Crm/backend/recordings");

    public RecordingService(RecordingRepository recordingRepository) {
        this.recordingRepository = recordingRepository;

        try {
            Files.createDirectories(recordingDirectory);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not create recording directory", e);
        }
    }

    public Recording uploadRecording(
            Long callLogId,
            Long employeeId,
            MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Recording file is empty");
        }

        try {

            String originalName = file.getOriginalFilename();

            if (originalName == null ||
                    originalName.isBlank()) {

                throw new RuntimeException(
                        "Invalid recording filename");
            }

            String fileName =
                    System.currentTimeMillis()
                    + "_"
                    + Paths.get(originalName)
                            .getFileName()
                            .toString();

            Path destination =
                    recordingDirectory.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            Recording recording = new Recording();

            recording.setCallLogId(callLogId);
            recording.setEmployeeId(employeeId);
            recording.setFileName(originalName);
            recording.setFilePath(destination.toString());
            recording.setFileType(file.getContentType());
            recording.setFileSize(file.getSize());

            return recordingRepository.save(recording);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to save recording", e);
        }
    }

    public List<Recording> getAllRecordings() {

        return recordingRepository.findAll();
    }

    public Recording getRecording(Long id) {

        return recordingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Recording not found"));
    }

    public void deleteRecording(Long id) {

    Recording recording =
            recordingRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Recording not found"));

    try {

        Path filePath =
                Paths.get(recording.getFilePath());

        // Delete physical file
        Files.deleteIfExists(filePath);

        // Delete database record
        recordingRepository.delete(recording);

    } catch (IOException e) {

        throw new RuntimeException(
                "Failed to delete recording", e);
    }
}
}