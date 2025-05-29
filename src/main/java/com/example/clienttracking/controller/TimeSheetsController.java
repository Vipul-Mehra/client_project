package com.example.clienttracking.controller;

import com.example.clienttracking.dto.TimeSheetDTO;
import com.example.clienttracking.service.TimeSheetsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/timeSheets")
@CrossOrigin(origins = "*")
public class TimeSheetsController {

    @Autowired
    private TimeSheetsService timeSheetsService;

    // Unified endpoint with all features
    @GetMapping
    public ResponseEntity<Page<TimeSheetDTO>> getTimeSheets(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entityDate

    ){

        List<TimeSheetDTO> result = timeSheetsService.search(keyword, startDate, endDate, entityDate);


        // Apply sorting
        if (sortBy != null && !sortBy.isEmpty()) {
            boolean isDesc = "desc".equalsIgnoreCase(sortDir);

            switch (sortBy.toLowerCase()) {
                case "hoursworked":
                    result.sort(Comparator.comparing(dto -> dto.getHoursWorked()));
                    break;
                case "resourceid":
                    result.sort(Comparator.comparing(dto -> dto.getResourceId()));
                    break;
                case "clientprojectid":
                    result.sort(Comparator.comparing(dto -> dto.getClientProjectId()));
                    break;
                default:
                    result.sort(Comparator.comparing(dto -> dto.getTimeSheetId()));
            }

            if (isDesc) {
                Collections.reverse(result);
            }
        }

        // Manual pagination
        int start = Math.min(page * size, result.size());
        int end = Math.min(start + size, result.size());

        List<TimeSheetDTO> pagedContent = result.subList(start, end);
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(new PageImpl<>(pagedContent, pageable, result.size()));
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<TimeSheetDTO> getWorkTimetableById(@PathVariable Long id) {
        TimeSheetDTO dto = timeSheetsService.getWorkTimetableById(id);
        return ResponseEntity.ok(dto);
    }

    // Create
    @PostMapping
    public ResponseEntity<TimeSheetDTO> createWorkTimetable(@RequestBody TimeSheetDTO dto) {
        TimeSheetDTO created = timeSheetsService.createWorkTimetable(dto);
        return ResponseEntity.status(201).body(created);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDTO> updateWorkTimetable(@PathVariable Long id, @RequestBody TimeSheetDTO dto) {
        TimeSheetDTO updated = timeSheetsService.updateWorkTimetable(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkTimetable(@PathVariable Long id) {
        timeSheetsService.deleteWorkTimetable(id);
        return ResponseEntity.noContent().build();
    }

//    keycloak coding

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
//        if (authService.authenticate(username, password)) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }


}

