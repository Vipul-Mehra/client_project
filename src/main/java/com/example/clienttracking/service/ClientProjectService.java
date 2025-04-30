package com.example.clienttracking.service;

import com.example.clienttracking.model.ClientProjects;
import java.util.List;

public interface ClientProjectService {
    List<ClientProjects> getAllClientProject();

    ClientProjects getClientProjectById(Long id);

    ClientProjects createClientProject(ClientProjects clientProject);

    ClientProjects updateClientProject(Long id, ClientProjects clientProjectDetails);

    void deleteClientProject(Long id);

}
