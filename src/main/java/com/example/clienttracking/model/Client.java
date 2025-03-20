package com.example.clienttracking.model;

import jakarta.persistence.*;

@Entity

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false)
    private String clientName;

    @Column(unique = true)
    private String clientEmail;
}
