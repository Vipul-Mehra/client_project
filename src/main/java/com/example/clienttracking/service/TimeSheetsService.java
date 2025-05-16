package com.example.clienttracking.service;

import com.example.clienttracking.dto.TimeSheetDTO;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

public interface TimeSheetsService {
    List<TimeSheetDTO> getAllWorkTimetables();
    TimeSheetDTO getWorkTimetableById(Long id);
    TimeSheetDTO createWorkTimetable(TimeSheetDTO dto);
    TimeSheetDTO updateWorkTimetable(Long id, TimeSheetDTO dto);
    void deleteWorkTimetable(Long id);
    Page<TimeSheetDTO> getPaginatedTimeSheets(int page, int size);


}