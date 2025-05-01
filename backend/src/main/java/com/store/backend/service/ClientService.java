package com.store.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.entities.Client;
import com.store.backend.repository.ClientRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Client registerClient(Client client) {
        return clientRepository.save(client);
    }
    
    public Client updateClient(Long id, Client clientUpdated) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            client.setName(clientUpdated.getName());
            client.setEmail(clientUpdated.getEmail());
            client.setPassword(clientUpdated.getPassword());

            return clientRepository.save(client);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }


    public void deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

}
