package com.example.clienttracking;

import com.example.clienttracking.controller.ClientProjectController;
import com.example.clienttracking.model.Client;
import com.example.clienttracking.model.ClientProject;
import com.example.clienttracking.model.ProjectTable;
import com.example.clienttracking.service.ClientProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
        ClientProject clientProject = getClientProject();

        when(clientProjectService.getClientProjectById(1L)).thenReturn(clientProject);

        mockMvc.perform(get("/clientprojects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.clientName").value("ABC Corp"))
                .andExpect(jsonPath("$.projectId.projectName").value("CRM System"));
    }

    @Test
    public void testGetAllClientProjects() throws Exception {
        ClientProject clientProject = getClientProject();
        List<ClientProject> clientProjects = Arrays.asList(clientProject);

        when(clientProjectService.getAllClientProject()).thenReturn(clientProjects);

        mockMvc.perform(get("/clientprojects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].client.clientName").value("ABC Corp"))
                .andExpect(jsonPath("$[0].projectId.projectName").value("CRM System"));
    }

    @Test
    public void testCreateClientProject() throws Exception {
        ClientProject clientProject = getClientProject();

        when(clientProjectService.createClientProject(Mockito.any(ClientProject.class))).thenReturn(clientProject);

        mockMvc.perform(post("/clientprojects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"client\":{\"clientId\":1,\"clientName\":\"ABC Corp\"}," +
                        "\"projectId\":{\"projectId\":1,\"projectName\":\"CRM System\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.clientName").value("ABC Corp"))
                .andExpect(jsonPath("$.projectId.projectName").value("CRM System"));
    }

    @Test
    public void testUpdateClientProject() throws Exception {
        ClientProject updatedClientProject = getClientProject();

        when(clientProjectService.updateClientProject(Mockito.eq(1L), Mockito.any(ClientProject.class)))
                .thenReturn(updatedClientProject);

        mockMvc.perform(put("/clientprojects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"client\":{\"clientId\":1,\"clientName\":\"ABC Corp\"}," +
                        "\"projectId\":{\"projectId\":1,\"projectName\":\"CRM System\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.clientName").value("ABC Corp"))
                .andExpect(jsonPath("$.projectId.projectName").value("CRM System"));
    }

    @Test
    public void testDeleteClientProject() throws Exception {
        doNothing().when(clientProjectService).deleteClientProject(1L);

        mockMvc.perform(delete("/clientprojects/1"))
                .andExpect(status().isOk());
    }

    // Helper method to create a sample ClientProject object
    private static ClientProject getClientProject() {
        Client client = new Client();
        client.setClientId(1L);
        client.setClientName("ABC Corp");

        ProjectTable project = new ProjectTable();
        project.setProjectId(1L);
        project.setProjectName("CRM System");

        ClientProject clientProject = new ClientProject();
        clientProject.setClientProjectId(1L);
        clientProject.setClient(client);
        clientProject.setProjectId(project);

        return clientProject;
    }
}
