package com.example.clienttracking.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "resources")
@Data
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    private String resourceName;
    private String resourceRole;
    @Version
    private Integer version;
}