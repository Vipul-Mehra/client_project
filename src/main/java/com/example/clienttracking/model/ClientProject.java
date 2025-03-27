package com.example.clienttracking.model;

import jakarta.persistence.*;

@Entity
public class ClientProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientProjectId;

    // Many-to-one relationship: A client can have multiple projects
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectTable projectId;

    // Getters and Setters
    public Long getClientProjectId() {
        return clientProjectId;
    }

    public void setClientProjectId(Long clientProjectId) {
        this.clientProjectId = clientProjectId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ProjectTable getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectTable projectId) {
        this.projectId = projectId;
    }
}
