package com.example.clienttracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.example.clienttracking.controller.ResourceController;
import com.example.clienttracking.model.Resource;
import com.example.clienttracking.service.ResourceService;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(MockitoExtension.class)
public class ResourceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ResourceService resourceService;

    @InjectMocks
    private ResourceController resourceController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();
    }

    @Test
    public void testGetResourceById() throws Exception {
        Resource resource = getResource();

        when(resourceService.getResourceById(1L)).thenReturn(resource);

        mockMvc.perform(get("/resources/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resourceName").value("John Doe"))
                .andExpect(jsonPath("$.resourceRole").value("Java Developer"));
    }

    private static Resource getResource() {
        Resource resource = new Resource();
        resource.setResourceId(1L);
        resource.setResourceName("John Doe");
        resource.setResourceRole("Java Developer");
        return resource;
    }

    @Test
    public void testGetAllResource() throws Exception {
        Resource resource = getResource();
        when(resourceService.getAllResources()).thenReturn(Arrays.asList(resource));

        mockMvc.perform(get("/resources"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].resourceName").value("John Doe"))
                .andExpect(jsonPath("$[0].resourceRole").value("Java Developer"));
    }

    @Test
    public void testCreateResource() throws Exception {
        Resource resource = getResource();

        when(resourceService.createResource(Mockito.any(Resource.class))).thenReturn(resource);

        mockMvc.perform(post("/resources").contentType(MediaType.APPLICATION_JSON)
                .content("{\"resourceName\":\"John Doe\",\"resourceRole\":\"Java Developer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resourceName").value("John Doe"))
                .andExpect(jsonPath("$.resourceRole").value("Java Developer"));
    }

    @Test
    public void testUpdateresource() throws Exception {
        Resource updatResource = getResource();

        when(resourceService.updateResource(Mockito.eq(1L), Mockito.any(Resource.class))).thenReturn(updatResource);

        mockMvc.perform(put("/resources/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"resourceName\":\"John Doe\",\"resourceRole\":\"Java Developer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resourceName").value("John Doe"))
                .andExpect(jsonPath("$.resourceRole").value("Java Developer"));
    }

    @Test
    public void testDeleteResource() throws Exception {
        Mockito.doNothing().when(resourceService).deleteResource(1L);

        mockMvc.perform(delete("/resources/1")).andExpect(status().isOk());
    }
}
