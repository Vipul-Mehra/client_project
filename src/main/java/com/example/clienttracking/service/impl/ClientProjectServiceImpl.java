package com.example.clienttracking.service.impl;

import com.example.clienttracking.model.ClientProjects;
import com.example.clienttracking.repository.ClientProjectRepository;
import com.example.clienttracking.service.ClientProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ClientProjects createClientProject(ClientProjects clientProject) {
        return clientProjectRepository.save(clientProject);
    }

    @Override
    public ClientProjects updateClientProject(Long id, ClientProjects clientProjectDetails) {
        ClientProjects clientProject = clientProjectRepository.findById(id).orElse(null);
        if (clientProject != null) {
            clientProject.setClient(clientProjectDetails.getClient());
            clientProject.setProject(clientProjectDetails.getProject());
            return clientProjectRepository.save(clientProject);
        }
        return null;
    }

    @Override
    public void deleteClientProject(Long id) {
        clientProjectRepository.deleteById(id);
    }
}