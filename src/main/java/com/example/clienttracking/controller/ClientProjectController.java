package com.example.clienttracking.controller;

import com.example.clienttracking.model.ClientProject;
import com.example.clienttracking.service.ClientProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientprojects")
@CrossOrigin(origins = "*")
public class ClientProjectController {

    @Autowired
    private ClientProjectService clientProjectService;

    @GetMapping
    public List<ClientProject> getAllClientProject() {
        return clientProjectService.getAllClientProject();
    }

    @GetMapping("/{id}")
    public ClientProject getClientProjectById(@PathVariable Long id) {
        return clientProjectService.getClientProjectById(id);
    }

    @PostMapping
    public ClientProject createClientProject(@RequestBody ClientProject clientProject) {
        return clientProjectService.createClientProject(clientProject);
    }

    @PutMapping("/{id}")
    public ClientProject updateClientProject(@PathVariable Long id, @RequestBody ClientProject clientProject) {
        return clientProjectService.updateClientProject(id, clientProject);
    }

    @DeleteMapping("/{id}")
    public void deleteClientProject(@PathVariable Long id) {
        clientProjectService.deleteClientProject(id);
    }
}
