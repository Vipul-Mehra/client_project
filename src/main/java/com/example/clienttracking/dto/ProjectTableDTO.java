package com.example.clienttracking.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProjectTableDTO {
    private Long projectId;
    private String projectName;
    private String description;
}