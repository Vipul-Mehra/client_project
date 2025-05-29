package com.example.clienttracking.service;

import com.example.clienttracking.dto.TimeSheetDTO;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;

public interface TimeSheetsService {
    List<TimeSheetDTO> getAllWorkTimetables();
    TimeSheetDTO getWorkTimetableById(Long id);
    TimeSheetDTO createWorkTimetable(TimeSheetDTO dto);
    TimeSheetDTO updateWorkTimetable(Long id, TimeSheetDTO dto);
    void deleteWorkTimetable(Long id);
    List<TimeSheetDTO> search(String keyword, LocalDate startDate, LocalDate endDate, LocalDate entityDate);

}