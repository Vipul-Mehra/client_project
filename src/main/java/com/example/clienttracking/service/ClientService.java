package com.example.clienttracking.service;

import com.example.clienttracking.model.Clients;
import java.util.List;

public interface ClientService {
    List<Clients> getAllClients();

    Clients getClientById(Long id);

    Clients createClient(Clients client);

    Clients updateClient(Long id, Clients clientDetails);

    void deleteClient(Long id);
}
