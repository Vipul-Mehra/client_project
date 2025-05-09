package com.example.clienttracking.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String clientName;
    private String clientEmail;
}