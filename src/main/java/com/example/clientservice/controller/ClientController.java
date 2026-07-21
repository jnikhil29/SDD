package com.example.clientservice.controller;

import com.example.clientservice.dto.ClientResponse;
import com.example.clientservice.dto.CreateClientRequest;
import com.example.clientservice.dto.UpdateClientRequest;
import com.example.clientservice.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody CreateClientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(request));
    }

    @PutMapping("/{coreClientId}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable String coreClientId, @Valid @RequestBody UpdateClientRequest request) {
        return ResponseEntity.ok(clientService.updateClient(coreClientId, request));
    }

    @DeleteMapping("/{coreClientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String coreClientId) {
        clientService.deleteClient(coreClientId);
        return ResponseEntity.noContent().build();
    }
}
