// src/main/java/com/example/clienttracking/service/impl/ClientProjectServiceImpl.java
package com.example.clienttracking.service.impl;

import com.example.clienttracking.model.ClientProjects;
import com.example.clienttracking.repository.ClientProjectRepository;
import com.example.clienttracking.service.ClientProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientProjectServiceImpl implements ClientProjectService {

    @Autowired
    private ClientProjectRepository clientProjectRepository;

    @Override
    public List<ClientProjects> getAllClientProject() {
        return clientProjectRepository.findAll();
    }

    @Override
    public ClientProjects getClientProjectById(Long id) {
        return clientProjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<ClientProjects> getClientProjectByClientId(Long id) {
        return clientProjectRepository.findByClient_Id(id).orElse(null);
    }

    @Override
    public ClientProjects createClientProject(ClientProjects clientProject) {
        return clientProjectRepository.save(clientProject);
    }

    @Override
    public ClientProjects updateClientProject(Long id, ClientProjects clientProjectDetails) throws NoSuchElementException {
        return clientProjectRepository.findById(id)
                .map(clientProject -> {
                    clientProject.setClient(clientProjectDetails.getClient());
                    clientProject.setProject(clientProjectDetails.getProject());
                    return clientProjectRepository.save(clientProject);
                })
                .orElseThrow(() -> new NoSuchElementException("ClientProject not found with id: " + id));
    }

    @Override
    public void deleteClientProject(Long id) {
        clientProjectRepository.deleteById(id);
    }
}