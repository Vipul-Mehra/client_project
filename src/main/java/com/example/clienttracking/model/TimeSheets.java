package com.example.clienttracking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "timesheets")
@Data

public class TimeSheets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeSheetId;

    private Date workDate;
    private Double hoursWorked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "client_project_id", nullable = false)
    private ClientProjects clientProject;
}