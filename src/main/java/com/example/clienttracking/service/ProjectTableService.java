package com.example.clienttracking.service;

import com.example.clienttracking.model.ProjectTable;
import java.util.List;

public interface ProjectTableService {
    List<ProjectTable> getAllProjectTable();

    ProjectTable getProjectTableById(Long id);

    ProjectTable createProjectTable(ProjectTable projectTable);

    ProjectTable updateProjectTable(Long id, ProjectTable projectTableDetails);

    void deleteProjectTable(Long id);
}
