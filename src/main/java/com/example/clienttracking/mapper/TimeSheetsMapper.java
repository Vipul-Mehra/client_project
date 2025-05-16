package com.example.clienttracking.mapper;

import com.example.clienttracking.dto.TimeSheetDTO;
import com.example.clienttracking.model.TimeSheets;

public class TimeSheetsMapper {

    public static TimeSheetDTO toDTO(TimeSheets entity) {
        TimeSheetDTO dto = new TimeSheetDTO();
        dto.setTimeSheetId(entity.getTimeSheetId());
        dto.setClientName(entity.getClientName());
        dto.setResourceName(entity.getResourceName());
        dto.setProjectName(entity.getProjectName());
        dto.setDescription(entity.getDescription());
        dto.setWorkDate(entity.getWorkDate() != null ? entity.getWorkDate().toString() : null);
        dto.setHoursWorked(entity.getHoursWorked());
        return dto;
    }
}
