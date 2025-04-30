//package com.example.clienttracking;
//
//import com.example.clienttracking.controller.ClientController;
//import com.example.clienttracking.model.Clients;
//import com.example.clienttracking.service.ClientService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ClientControllerTest {
//
//	private MockMvc mockMvc;
//
//	@Mock
//	private ClientService clientService;
//
//	@InjectMocks
//	private ClientController clientController;
//
//	@BeforeEach
//	public void setup() {
//		mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
//	}
//
//	private static Clients getClient() {
//		Clients client = new Clients();
//		client.setId(1L);
//		client.setClientName("ABC Corp");
//		client.setClientEmail("abc@example.com");
//		return client;
//	}
//
//	@Test
//	public void testGetClientById() throws Exception {
//		Clients client = getClient();
//		when(clientService.getClientById(1L)).thenReturn(client);
//
//		mockMvc.perform(get("/api/clients/1"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.clientName").value("ABC Corp"))
//				.andExpect(jsonPath("$.clientEmail").value("abc@example.com"));
//	}
//
//	@Test
//	public void testGetAllClients() throws Exception {
//		Clients client1 = getClient();
//		Clients client2 = new Clients();
//		client2.setId(2L);
//		client2.setClientName("XYZ Ltd");
//		client2.setClientEmail("xyz@example.com");
//
//		when(clientService.getAllClients()).thenReturn(Arrays.asList(client1, client2));
//
//		mockMvc.perform(get("/api/clients"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$[0].clientName").value("ABC Corp"))
//				.andExpect(jsonPath("$[1].clientName").value("XYZ Ltd"));
//	}
//
//	@Test
//	public void testCreateClient() throws Exception {
//		Clients client = getClient();
//		when(clientService.createClient(Mockito.any(Clients.class))).thenReturn(client);
//
//		mockMvc.perform(post("/api/clients")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"clientName\": \"ABC Corp\", \"clientEmail\": \"abc@example.com\"}"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.clientName").value("ABC Corp"))
//				.andExpect(jsonPath("$.clientEmail").value("abc@example.com"));
//	}
//
//	@Test
//	public void testUpdateClient() throws Exception {
//		Clients updatedClient = new Clients();
//		updatedClient.setId(1L);
//		updatedClient.setClientName("XYZ Corp");
//		updatedClient.setClientEmail("xyz@example.com");
//
//		when(clientService.updateClient(Mockito.eq(1L), Mockito.any(Clients.class))).thenReturn(updatedClient);
//
//		mockMvc.perform(put("/api/clients/1")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"clientName\": \"XYZ Corp\", \"clientEmail\": \"xyz@example.com\"}"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.clientName").value("XYZ Corp"))
//				.andExpect(jsonPath("$.clientEmail").value("xyz@example.com"));
//	}
//
//	@Test
//	public void testDeleteClient() throws Exception {
//		Mockito.doNothing().when(clientService).deleteClient(1L);
//		mockMvc.perform(delete("/api/clients/1"))
//				.andExpect(status().isOk());
//	}
//}
