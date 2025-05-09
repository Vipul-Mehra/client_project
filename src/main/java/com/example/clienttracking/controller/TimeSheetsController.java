package com.example.clienttracking.controller;

import com.example.clienttracking.dto.TimeSheetDTO;
import com.example.clienttracking.service.TimeSheetsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/timeSheets")
public class TimeSheetsController {

    @Autowired
    private TimeSheetsService timeSheetsService;

    @GetMapping
    public ResponseEntity<List<TimeSheetDTO>> getAllWorkTimetables() {
        return new ResponseEntity<>(timeSheetsService.getAllWorkTimetables(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeSheetDTO> getWorkTimetableById(@PathVariable Long id) {
        TimeSheetDTO dto = timeSheetsService.getWorkTimetableById(id);
        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TimeSheetDTO> createWorkTimetable(@RequestBody TimeSheetDTO dto) {
        try {
            TimeSheetDTO created = timeSheetsService.createWorkTimetable(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating TimeSheet: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDTO> updateWorkTimetable(@PathVariable Long id, @RequestBody TimeSheetDTO dto) {
        try {
            TimeSheetDTO updated = timeSheetsService.updateWorkTimetable(id, dto);
            return updated != null ? new ResponseEntity<>(updated, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Error updating TimeSheet: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkTimetable(@PathVariable Long id) {
        timeSheetsService.deleteWorkTimetable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}