package com.example.clienttracking.service;

import com.example.clienttracking.model.ClientProject;
import java.util.List;

public interface ClientProjectService {
    List<ClientProject> getAllClientProject();

    ClientProject getClientProjectById(Long id);

    ClientProject createClientProject(ClientProject clientProject);

    ClientProject updateClientProject(Long id, ClientProject clientProjectDetails);

    void deleteClientProject(Long id);

}
