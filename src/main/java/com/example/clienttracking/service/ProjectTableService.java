package com.example.clienttracking.service;

import com.example.clienttracking.model.Projects;
import java.util.List;

public interface ProjectTableService {
    List<Projects> getAllProjectTable();

    Projects getProjectTableById(Long id);

    Projects createProjectTable(Projects projectTable);

    Projects updateProjectTable(Long id, Projects projectTableDetails);

    void deleteProjectTable(Long id);
}
