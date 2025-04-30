package com.example.clienttracking.service;

import com.example.clienttracking.dto.TimeSheetDTO;
import java.util.List;

public interface TimeSheetsService {
    List<TimeSheetDTO> getAllWorkTimetables();
    TimeSheetDTO getWorkTimetableById(Long id);
    TimeSheetDTO createWorkTimetable(TimeSheetDTO dto);
    TimeSheetDTO updateWorkTimetable(Long id, TimeSheetDTO dto);
    void deleteWorkTimetable(Long id);
}