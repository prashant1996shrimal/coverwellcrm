package com.coverwell.crm.controller;

import com.coverwell.crm.entity.Recording;
import com.coverwell.crm.service.RecordingService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/recordings")
@CrossOrigin(origins = "*")
public class RecordingController {

    private final RecordingService recordingService;

    public RecordingController(
            RecordingService recordingService) {

        this.recordingService = recordingService;
    }

    // Upload recording

    @PostMapping("/upload")
    public Recording uploadRecording(

            @RequestParam Long callLogId,

            @RequestParam Long employeeId,

            @RequestParam("file") MultipartFile file) {

        return recordingService.uploadRecording(
                callLogId,
                employeeId,
                file);
    }

    // Get all recordings

    @GetMapping
    public List<Recording> getAllRecordings() {

        return recordingService.getAllRecordings();
    }

    // Get one recording

    @GetMapping("/{id}")
    public Recording getRecording(
            @PathVariable Long id) {

        return recordingService.getRecording(id);
    }

    // Play recording

    @GetMapping("/play/{id}")
public ResponseEntity<Resource> playRecording(@PathVariable Long id) {

    try {

        Recording recording = recordingService.getRecording(id);

        Path path = Paths.get(recording.getFilePath());

        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" +
                                recording.getFileName() +
                                "\""
                )
                .body(resource);

    } catch (Exception e) {

        e.printStackTrace();

        return ResponseEntity.internalServerError().build();
    }
}

// Download recording
@GetMapping("/download/{id}")
public ResponseEntity<Resource> downloadRecording(
        @PathVariable Long id) {

    try {

        Recording recording =
                recordingService.getRecording(id);

        Path path =
                Paths.get(recording.getFilePath());

        Resource resource =
                new UrlResource(path.toUri());

        if (!resource.exists() ||
                !resource.isReadable()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                recording.getFileName() +
                                "\""
                )
                .body(resource);

    } catch (Exception e) {

        e.printStackTrace();

        return ResponseEntity
                .internalServerError()
                .build();
    }
}


// Delete recording
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteRecording(
        @PathVariable Long id) {

    recordingService.deleteRecording(id);

    return ResponseEntity.noContent().build();
}
}