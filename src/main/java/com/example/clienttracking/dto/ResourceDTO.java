package com.example.clienttracking.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResourceDTO {
    private Long resourceId;
    private String resourceName;
    private String resourceRole;
}