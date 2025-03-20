package com.example.clienttracking.service;

import com.example.clienttracking.model.Client;
import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client getClientById(Long id);

    Client createClient(Client client);

    Client updateClient(Long id, Client clientDetails);

    void deleteClient(Long id);
}
