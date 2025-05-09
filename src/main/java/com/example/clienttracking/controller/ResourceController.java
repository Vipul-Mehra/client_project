package com.example.clienttracking.controller;

import com.example.clienttracking.dto.ResourceDTO;
import com.example.clienttracking.model.Resource;
import com.example.clienttracking.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resources")
@CrossOrigin(origins = "http://localhost:4200")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    // Utility method to convert Resource to ResourceDTO
    private ResourceDTO convertToDTO(Resource resource) {
        ResourceDTO dto = new ResourceDTO();
        dto.setResourceId(resource.getResourceId());
        dto.setResourceName(resource.getResourceName());
        dto.setResourceRole(resource.getResourceRole());
        return dto;
    }

    // Utility method to convert ResourceDTO to Resource

    private Resource convertToEntity(ResourceDTO dto) {
        Resource resource = new Resource();
        if (dto.getResourceId() != null && dto.getResourceId() != 0) { // Add this check
            resource.setResourceId(dto.getResourceId());
        }
        resource.setResourceName(dto.getResourceName());
        resource.setResourceRole(dto.getResourceRole());
        return resource;
    }

    @GetMapping
    public List<ResourceDTO> getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        return resources.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResourceDTO getResourceById(@PathVariable Long id) {
        Resource resource = resourceService.getResourceById(id);
        if (resource != null) {
            return convertToDTO(resource);
        }
        return null; // Or throw an exception if resource not found
    }

    @PostMapping
    public ResourceDTO createResource(@RequestBody ResourceDTO resourceDTO) {
        Resource resource = convertToEntity(resourceDTO);
        Resource createdResource = resourceService.createResource(resource);
        return convertToDTO(createdResource);
    }

    @PutMapping("/{id}")
    public ResourceDTO updateResource(@PathVariable Long id, @RequestBody ResourceDTO resourceDTO) {
        Resource resource = convertToEntity(resourceDTO); // Use the dto to create entity.
        Resource updatedResource = resourceService.updateResource(id, resource);
        if (updatedResource != null) {
            return convertToDTO(updatedResource);
        }
        return null; // Or throw an exception
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }
}

