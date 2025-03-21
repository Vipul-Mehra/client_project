package com.example.clienttracking.service;

import com.example.clienttracking.model.WorkTimetable;
import java.util.List;

public interface WorkTimetableService {
    List<WorkTimetable> getAllWorkTimetables();

    WorkTimetable getWorkTimetableById(Long id);

    WorkTimetable createWorkTimetable(WorkTimetable workTimetable);

    WorkTimetable updateWorkTimetable(Long id, WorkTimetable workTimetableDetails);

    void deleteWorkTimetable(Long id);
}
