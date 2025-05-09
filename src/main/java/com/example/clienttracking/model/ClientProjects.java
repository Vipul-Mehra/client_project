package com.example.clienttracking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class ClientProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientProjectId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Clients client; // Changed from Client to Clients

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Projects project;

}
