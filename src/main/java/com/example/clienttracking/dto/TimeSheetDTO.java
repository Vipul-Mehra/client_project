package com.example.clienttracking.dto;

import lombok.Data;

@Data
public class TimeSheetDTO {
    private Long timeSheetId;
    private Long resourceId;

    private String resourceName;
    private String resourceRole;

    private Long clientProjectId;
    private String clientName;
    private String projectName;
    private String workDate;
    private Double hoursWorked;
}