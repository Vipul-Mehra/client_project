package com.example.clienttracking.service.impl;

import com.example.clienttracking.model.Resource;
import com.example.clienttracking.repository.ResourceRepository;
import com.example.clienttracking.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource updateResource(Long id, Resource resourceDetails) {
        Resource resource = resourceRepository.findById(id).orElse(null);
        if (resource != null) {
            // Only update fields if they are not null in the incoming data
            if (resourceDetails.getResourceName() != null) {
                resource.setResourceName(resourceDetails.getResourceName());
            }
            if (resourceDetails.getResourceRole() != null) {
                resource.setResourceRole(resourceDetails.getResourceRole());
            }
            return resourceRepository.save(resource);
        }
        return null;
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
