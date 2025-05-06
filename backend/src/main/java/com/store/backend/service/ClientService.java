package com.store.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.dto.ClientRequestDTO;
import com.store.backend.dto.ClientResponseDTO;
import com.store.backend.entities.Client;
import com.store.backend.repository.ClientRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
    private ClientRepository clientRepository;

    public List<ClientResponseDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientResponseDTO> dtos = new ArrayList<>();

        for (Client client : clients) {
            dtos.add(toDTO(client));
        }

        return dtos;
    }

    public ClientResponseDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return toDTO(client);
    }

    public ClientResponseDTO registerClient(ClientRequestDTO dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword()); // senha salva em texto puro (por enquanto)
        client.setCpf(dto.getCpf());

        Client saved = clientRepository.save(client);
        return toDTO(saved);
    }

    public ClientResponseDTO updateClient(Long id, ClientRequestDTO dto) {
        Optional<Client> clientOpt = clientRepository.findById(id);

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            client.setName(dto.getName());
            client.setEmail(dto.getEmail());
            client.setPassword(dto.getPassword());
            client.setCpf(dto.getCpf());

            Client updated = clientRepository.save(client);
            return toDTO(updated);
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

    private ClientResponseDTO toDTO(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setCpf(client.getCpf());
        return dto;
    }

}
