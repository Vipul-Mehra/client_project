package com.example.clienttracking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ClientProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientProjectId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Clients client;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Projects project;

    // One-to-Many relationship with TimeSheets
    @OneToMany(mappedBy = "clientProject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TimeSheets> timeSheets;
}