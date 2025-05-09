package com.example.clienttracking.service;

import com.example.clienttracking.model.Resource;
import java.util.List;

public interface ResourceService {
    List<Resource> getAllResources();

    Resource getResourceById(Long id);

    Resource createResource(Resource resource);

    Resource updateResource(Long id, Resource resourceDetails);

    void deleteResource(Long id);
}