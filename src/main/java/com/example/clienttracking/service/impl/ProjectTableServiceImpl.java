package com.example.clienttracking.service.impl;

import com.example.clienttracking.model.Projects;
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
    public List<Projects> getAllProjectTable() {
        return projectTableRepository.findAll();
    }

    @Override
    public Projects getProjectTableById(Long id) {
        return projectTableRepository.findById(id).orElse(null);
    }

    @Override
    public Projects createProjectTable(Projects projectTable) {
        return projectTableRepository.save(projectTable);
    }

    @Override
    public void deleteProjectTable(Long id) {
        projectTableRepository.deleteById(id);
    }

    @Override
    public Projects updateProjectTable(Long id, Projects projectTableDetails) {
        Projects projectTable = projectTableRepository.findById(id).orElse(null);
        if (projectTable != null) {
            projectTable.setDescription(projectTableDetails.getDescription());
            projectTable.setProjectName(projectTableDetails.getProjectName());
            return projectTableRepository.save(projectTable);
        }
        return null;
    }
}