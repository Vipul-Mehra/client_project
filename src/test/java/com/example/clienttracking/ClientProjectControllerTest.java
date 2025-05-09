package com.example.clienttracking;

import com.example.clienttracking.controller.ClientProjectController;
import com.example.clienttracking.model.Clients;
import com.example.clienttracking.model.ClientProjects;
import com.example.clienttracking.model.Projects;
import com.example.clienttracking.service.ClientProjectService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ClientProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientProjectService clientProjectService;

    @InjectMocks
    private ClientProjectController clientProjectController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientProjectController).build();
    }

    @Test
    public void testGetClientProjectById() throws Exception {
        ClientProjects clientProject = getClientProject();

        when(clientProjectService.getClientProjectById(1L)).thenReturn(clientProject);

        mockMvc.perform(get("/clientprojects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.clientName").value("ABC Corp"))
                .andExpect(jsonPath("$.project.projectName").value("CRM System"));
    }

    @Test
    public void testGetAllClientProjects() throws Exception {
        ClientProjects clientProject = getClientProject();
        List<ClientProjects> clientProjects = Arrays.asList(clientProject);

        when(clientProjectService.getAllClientProject()).thenReturn(clientProjects);

        mockMvc.perform(get("/clientprojects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].client.clientName").value("ABC Corp"))
                .andExpect(jsonPath("$[0].project.projectName").value("CRM System"));
    }

    @Test
    public void testCreateClientProject() throws Exception {
        ClientProjects clientProject = getClientProject();

        when(clientProjectService.createClientProject(Mockito.any(ClientProjects.class))).thenReturn(clientProject);

        mockMvc.perform(post("/clientprojects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"client\":{\"clientId\":1,\"clientName\":\"ABC Corp\"}," +
                                "\"project\":{\"projectId\":1,\"projectName\":\"CRM System\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client.clientName").value("ABC Corp"))
                .andExpect(jsonPath("$.project.projectName").value("CRM System"));
    }

    @Test
    public void testUpdateClientProject() throws Exception {
        ClientProjects updatedClientProject = getClientProject();

        when(clientProjectService.updateClientProject(Mockito.eq(1L), Mockito.any(ClientProjects.class)))
                .thenReturn(updatedClientProject);

        mockMvc.perform(put("/clientprojects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"client\":{\"clientId\":1,\"clientName\":\"ABC Corp\"}," +
                                "\"project\":{\"projectId\":1,\"projectName\":\"CRM System\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.clientName").value("ABC Corp"))
                .andExpect(jsonPath("$.project.projectName").value("CRM System"));
    }

    @Test
    public void testDeleteClientProject() throws Exception {
        doNothing().when(clientProjectService).deleteClientProject(1L);

        mockMvc.perform(delete("/clientprojects/1"))
                .andExpect(status().isNoContent());
    }

    // Helper method to create a sample ClientProject object
    private static ClientProjects getClientProject() {
        Clients client = new Clients();
        client.setId(1L);
        client.setClientName("ABC Corp");

        Projects project = new Projects();
        project.setProjectId(1L);
        project.setProjectName("CRM System");

        ClientProjects clientProject = new ClientProjects();
        clientProject.setClientProjectId(1L);
        clientProject.setClient(client);
        clientProject.setProject(project);

        return clientProject;
    }
}