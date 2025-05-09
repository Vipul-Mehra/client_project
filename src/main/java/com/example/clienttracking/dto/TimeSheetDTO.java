package com.example.clienttracking.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TimeSheetDTO {
    private Long timeSheetId;
    private Long resourceId;
    private Long clientProjectId;
    private String workDate;
    private Double hoursWorked;
}
