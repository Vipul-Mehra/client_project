package com.example.clienttracking.service.impl;

import com.example.clienttracking.model.ProjectTable;
import com.example.clienttracking.repository.ProjectTableRepository;
import com.example.clienttracking.service.ProjectTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectTableServiceImpl implements ProjectTableService {

    @Autowired
    private ProjectTableRepository projectTableRepository;

    @Override
    public List<ProjectTable> getAllProjectTable() {
        return projectTableRepository.findAll();
    }

    @Override
    public ProjectTable getProjectTableById(Long id) {
        return projectTableRepository.findById(id).orElse(null);
    }

    @Override
    public ProjectTable createProjectTable(ProjectTable projectTable) {
        return projectTableRepository.save(projectTable);
    }

    @Override
    public void deleteProjectTable(Long id) {
        projectTableRepository.deleteById(id);
    }

    @Override
    public ProjectTable updateProjectTable(Long id, ProjectTable projectTableDetails) {
        ProjectTable projectTable = projectTableRepository.findById(id).orElse(null);

        if (projectTable != null) {
            projectTable.setDiscription(projectTableDetails.getDiscription());
            projectTable.setProjectName(projectTableDetails.getProjectName());
            return projectTableRepository.save(projectTable);
        }

        return null;
    }
}
