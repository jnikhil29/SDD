package com.example.clientservice.service;

import com.example.clientservice.dto.ClientResponse;
import com.example.clientservice.dto.CreateClientRequest;
import com.example.clientservice.dto.UpdateClientRequest;
import com.example.clientservice.model.Client;
import com.example.clientservice.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientResponse createClient(CreateClientRequest request) {
        if (request.getCoreClientId() == null || request.getCoreClientId().isBlank()) {
            throw new IllegalArgumentException("coreClientId is required");
        }
        if (request.getClientName() == null || request.getClientName().isBlank()) {
            throw new IllegalArgumentException("clientName is required");
        }
        if (request.getOnboardingDate() == null) {
            throw new IllegalArgumentException("onboardingDate is required");
        }
        if (clientRepository.existsByCoreClientId(request.getCoreClientId())) {
            throw new IllegalArgumentException("coreClientId already exists");
        }
        if (clientRepository.existsByClientName(request.getClientName())) {
            throw new IllegalArgumentException("clientName already exists");
        }

        Client client = new Client(request.getCoreClientId(), request.getClientName(), request.getOnboardingDate());
        Client saved = clientRepository.save(client);
        return new ClientResponse(saved.getId(), saved.getCoreClientId(), saved.getClientName(), saved.getOnboardingDate());
    }

    @Transactional
    public ClientResponse updateClient(String coreClientId, UpdateClientRequest request) {
        Client existing = clientRepository.findByCoreClientId(coreClientId)
                .orElseThrow(() -> new IllegalArgumentException("Client with coreClientId does not exist"));

        if (request.getClientName() == null || request.getClientName().isBlank()) {
            throw new IllegalArgumentException("clientName is required");
        }
        if (request.getOnboardingDate() == null) {
            throw new IllegalArgumentException("onboardingDate is required");
        }
        Optional<Client> sameNameClient = clientRepository.findAll().stream()
                .filter(client -> !client.getId().equals(existing.getId()))
                .filter(client -> client.getClientName().equalsIgnoreCase(request.getClientName()))
                .findFirst();
        if (sameNameClient.isPresent()) {
            throw new IllegalArgumentException("clientName already exists");
        }

        existing.setClientName(request.getClientName());
        existing.setOnboardingDate(request.getOnboardingDate());
        Client updated = clientRepository.save(existing);
        return new ClientResponse(updated.getId(), updated.getCoreClientId(), updated.getClientName(), updated.getOnboardingDate());
    }

    @Transactional
    public void deleteClient(String coreClientId) {
        Client existing = clientRepository.findByCoreClientId(coreClientId)
                .orElseThrow(() -> new IllegalArgumentException("Client with coreClientId does not exist"));
        clientRepository.delete(existing);
    }
}
