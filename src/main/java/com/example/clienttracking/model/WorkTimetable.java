package com.example.clienttracking.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity

public class WorkTimetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Temporal(TemporalType.DATE)
    private Date workDate;

    private Double hoursWorked;
}
