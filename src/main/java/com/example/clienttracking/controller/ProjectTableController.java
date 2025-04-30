package com.example.clienttracking.controller;

import com.example.clienttracking.model.Projects;
import com.example.clienttracking.service.ProjectTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projecttable")
@CrossOrigin(origins = "*")
public class ProjectTableController {
    @Autowired
    private ProjectTableService projectTableService;

    @GetMapping
    public List<Projects> getAllProjectTables() {
        return projectTableService.getAllProjectTable();
    }

    @GetMapping("/{id}")
    public Projects getProjectTableById(@PathVariable Long id) {
        return projectTableService.getProjectTableById(id);
    }

    @PostMapping
    public Projects createProjectTable(@RequestBody Projects projectTable) {
        return projectTableService.createProjectTable(projectTable);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectTable(@PathVariable Long id) {
        projectTableService.deleteProjectTable(id);
    }

    @PutMapping("/{id}")
    public Projects updateProjectTable(@PathVariable Long id, @RequestBody Projects projectTable) {
        return projectTableService.updateProjectTable(id, projectTable);
    }

}
