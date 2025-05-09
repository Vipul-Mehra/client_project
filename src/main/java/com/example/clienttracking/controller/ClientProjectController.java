// src/main/java/com/example/clienttracking/controller/ClientProjectController.java
package com.example.clienttracking.controller;

import com.example.clienttracking.model.ClientProjects;
import com.example.clienttracking.service.ClientProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/clientprojects")
@CrossOrigin(origins = "*")
public class ClientProjectController {

    @Autowired
    private ClientProjectService clientProjectService;

    @GetMapping
    public List<ClientProjects> getAllClientProject() {
        return clientProjectService.getAllClientProject();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientProjects> getClientProjectById(@PathVariable Long id) {
        ClientProjects clientProject = clientProjectService.getClientProjectById(id);
        if (clientProject != null) {
            return new ResponseEntity<>(clientProject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<ClientProjects>> getClientProjectByClientId(@PathVariable Long id) {
        return new ResponseEntity<>(clientProjectService.getClientProjectByClientId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientProjects> createClientProject(@RequestBody ClientProjects clientProject) {
        ClientProjects createdProject = clientProjectService.createClientProject(clientProject);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientProjects> updateClientProject(@PathVariable Long id, @RequestBody ClientProjects clientProject) {
        try {
            ClientProjects updatedProject = clientProjectService.updateClientProject(id, clientProject);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientProject(@PathVariable Long id) {
        clientProjectService.deleteClientProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}