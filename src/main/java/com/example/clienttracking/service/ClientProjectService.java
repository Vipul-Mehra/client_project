package com.example.clienttracking.service;

import com.example.clienttracking.model.ClientProjects;

import java.util.List;
import java.util.NoSuchElementException;

public interface ClientProjectService {
    List<ClientProjects> getAllClientProject();

    ClientProjects getClientProjectById(Long id);
    List<ClientProjects> getClientProjectByClientId(Long id);

    ClientProjects createClientProject(ClientProjects clientProject);

    ClientProjects updateClientProject(Long id, ClientProjects clientProjectDetails) throws NoSuchElementException;

    void deleteClientProject(Long id);
}