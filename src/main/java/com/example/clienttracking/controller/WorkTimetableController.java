package com.example.clienttracking.controller;

import com.example.clienttracking.model.WorkTimetable;
import com.example.clienttracking.service.WorkTimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worktimetables")
public class WorkTimetableController {

    @Autowired
    private WorkTimetableService workTimetableService;

    @GetMapping
    public List<WorkTimetable> getAllWorkTimetables() {
        return workTimetableService.getAllWorkTimetables();
    }

    @GetMapping("/{id}")
    public WorkTimetable getWorkTimetableById(@PathVariable Long id) {
        return workTimetableService.getWorkTimetableById(id);
    }

    @PostMapping
    public WorkTimetable createWorkTimetable(@RequestBody WorkTimetable workTimetable) {
        return workTimetableService.createWorkTimetable(workTimetable);
    }

    @PutMapping("/{id}")
    public WorkTimetable updateWorkTimetable(@PathVariable Long id, @RequestBody WorkTimetable workTimetableDetails) {
        return workTimetableService.updateWorkTimetable(id, workTimetableDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkTimetable(@PathVariable Long id) {
        workTimetableService.deleteWorkTimetable(id);
    }
}
