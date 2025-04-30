package com.example.clienttracking.controller;

import com.example.clienttracking.model.Clients;
import com.example.clienttracking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Clients> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Clients getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Clients createClient(@RequestBody Clients client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Clients updateClient(@PathVariable Long id, @RequestBody Clients clientDetails) {
        return clientService.updateClient(id, clientDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
