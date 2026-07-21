package com.example.clientservice.controller;

import com.example.clientservice.dto.CreateClientRequest;
import com.example.clientservice.dto.UpdateClientRequest;
import com.example.clientservice.model.Client;
import com.example.clientservice.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setup() {
        clientRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin123")
    void shouldCreateClientWhenValidRequest() throws Exception {
        String payload = "{\"coreClientId\":\"C001\",\"clientName\":\"Acme\",\"onboardingDate\":\"2026-01-15\"}";
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.coreClientId").value("C001"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin123")
    void shouldRejectCreateWhenMissingRequiredField() throws Exception {
        String payload = "{\"coreClientId\":\"C001\",\"clientName\":\"Acme\"}";
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("onboardingDate is required")));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin123")
    void shouldUpdateClientWhenExistingCoreClientId() throws Exception {
        clientRepository.save(new Client("C001", "Acme", java.time.LocalDate.of(2026, 1, 15)));
        String payload = "{\"clientName\":\"Acme Updated\",\"onboardingDate\":\"2026-02-01\"}";
        mockMvc.perform(put("/clients/C001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientName").value("Acme Updated"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin123")
    void shouldRejectUpdateWhenClientDoesNotExist() throws Exception {
        String payload = "{\"clientName\":\"Acme Updated\",\"onboardingDate\":\"2026-02-01\"}";
        mockMvc.perform(put("/clients/MISSING")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Client with coreClientId does not exist")));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin123")
    void shouldDeleteClientWhenExistingCoreClientId() throws Exception {
        clientRepository.save(new Client("C001", "Acme", java.time.LocalDate.of(2026, 1, 15)));
        mockMvc.perform(delete("/clients/C001"))
                .andExpect(status().isNoContent());
    }
}
