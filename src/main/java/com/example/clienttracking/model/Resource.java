package com.example.clienttracking.model;

import jakarta.persistence.*;

@Entity

public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    @Column(nullable = false)
    private String resourceName;

    private String resourceRole;
}
