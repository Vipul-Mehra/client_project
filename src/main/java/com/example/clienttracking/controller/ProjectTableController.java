package com.example.clienttracking.controller;

import com.example.clienttracking.model.ProjectTable;
import com.example.clienttracking.service.ProjectTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projecttables")
@CrossOrigin(origins = "*")
public class ProjectTableController {
    @Autowired
    private ProjectTableService projectTableService;

    @GetMapping
    public List<ProjectTable> getAllProjectTables() {
        return projectTableService.getAllProjectTable();
    }

    @GetMapping("/{id}")
    public ProjectTable getProjectTableById(@PathVariable Long id) {
        return projectTableService.getProjectTableById(id);
    }

    @PostMapping
    public ProjectTable createProjectTable(@RequestBody ProjectTable projectTable) {
        return projectTableService.createProjectTable(projectTable);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectTable(@PathVariable Long id) {
        projectTableService.deleteProjectTable(id);
    }

    @PutMapping("/{id}")
    public ProjectTable updateProjectTable(@PathVariable Long id, @RequestBody ProjectTable projectTable) {
        return projectTableService.updateProjectTable(id, projectTable);
    }

}
