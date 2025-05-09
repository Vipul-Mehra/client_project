package com.example.clienttracking.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClientProjectDTO {
    private Long clientProjectId;
    private Long clientId;
    private Long projectId;
}