package com.example.clientservice.dto;

import java.time.LocalDate;

public class ClientResponse {
    private Long id;
    private String coreClientId;
    private String clientName;
    private LocalDate onboardingDate;

    public ClientResponse(Long id, String coreClientId, String clientName, LocalDate onboardingDate) {
        this.id = id;
        this.coreClientId = coreClientId;
        this.clientName = clientName;
        this.onboardingDate = onboardingDate;
    }

    public Long getId() { return id; }
    public String getCoreClientId() { return coreClientId; }
    public String getClientName() { return clientName; }
    public LocalDate getOnboardingDate() { return onboardingDate; }
}
