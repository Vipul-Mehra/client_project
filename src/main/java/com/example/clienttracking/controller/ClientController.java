package com.example.clienttracking.controller;

import com.example.clienttracking.model.Client;
import com.example.clienttracking.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @Operation(summary = "get all data of client controller", description = "get all of the data from the table of client controller")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get data by id of client controller", description = "get the data by the id from the client controller ")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    @Operation(summary = "add the data in the client controller", description = "add the data in the client controller ")
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update the data in the client controller", description = "update the data in the client controller using id")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientService.updateClient(id, clientDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete the data in the client controller", description = "delete the data in the client controller using id")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
