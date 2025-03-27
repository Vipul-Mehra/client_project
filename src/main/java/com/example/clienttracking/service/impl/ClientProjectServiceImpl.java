package com.example.clienttracking.service.impl;

import com.example.clienttracking.model.ClientProject;
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
    public List<ClientProject> getAllClientProject() {
        return clientProjectRepository.findAll();
    }

    @Override
    public ClientProject getClientProjectById(Long id) {
        return clientProjectRepository.findById(id).orElse(null);
    }

    @Override
    public ClientProject createClientProject(ClientProject clientProject) {
        return clientProjectRepository.save(clientProject);
    }

    @Override
    public ClientProject updateClientProject(Long id, ClientProject clientProjectDetails) {
        ClientProject clientProject = clientProjectRepository.findById(id).orElse(null);

        if (clientProject != null) {
            clientProject.setClient(clientProjectDetails.getClient());
            clientProject.setProjectId(clientProjectDetails.getProjectId());
            return clientProjectRepository.save(clientProject);
        }
        return null;
    }

    @Override
    public void deleteClientProject(Long id) {
        clientProjectRepository.deleteById(id);
    }

}