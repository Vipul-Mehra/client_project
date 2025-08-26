package com.example.clienttracking.service.impl;

// import com.example.clienttracking.model.Clients; // Remove this import if Clients model is not used
import com.example.clienttracking.model.Clients;
import com.example.clienttracking.repository.ClientRepository;
import com.example.clienttracking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Clients> getAllClients() { // Changed return type to List<AppClient>
        List<Clients> clients = clientRepository.findAll();
        clients.forEach(client -> {
            // These methods should now be found if AppClient.java is correct
            if ("null".equals(client.getClientName()))
                client.setClientName(null);
            if ("null".equals(client.getClientEmail()))
                client.setClientEmail(null);
        });
        return clients;
    }

    @Override
    public Clients getClientById(Long id) { // Changed return type to AppClient
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Clients createClient(Clients client) { // Changed parameter and return type to AppClient
        return clientRepository.save(client);
    }

    @Override
    public Clients updateClient(Long id, Clients clientDetails) { // Changed parameter and return type to AppClient
        Clients client = clientRepository.findById(id).orElse(null); // Changed type to AppClient
        if (client != null) {
            // These methods should now be found if AppClient.java is correct
            client.setClientName(clientDetails.getClientName());
            client.setClientEmail(clientDetails.getClientEmail());
            return clientRepository.save(client);
        }
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}