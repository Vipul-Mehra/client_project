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
    public List<ClientProject> getAllClientProjects() {
        return clientProjectRepository.findAll();
    }

    @Override
    public ClientProject getClientProjectById(Long id) {
        return ClientProjectRepository.findById(id).orElse(null);
    }

    @Override
    public ClientProject creaClientProject(ClientProject clientProject) {
        return clientProjectRepository.save(clientProject);
    }

    @Override
    public void deleteClientProject(Long id) {
        clientProjectRepository.deleteById(id);
    }

}
