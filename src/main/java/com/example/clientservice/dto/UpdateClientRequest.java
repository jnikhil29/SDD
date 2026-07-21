package com.example.clientservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class UpdateClientRequest {
    @NotBlank(message = "clientName is required")
    private String clientName;

    @NotNull(message = "onboardingDate is required")
    private LocalDate onboardingDate;

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public LocalDate getOnboardingDate() { return onboardingDate; }
    public void setOnboardingDate(LocalDate onboardingDate) { this.onboardingDate = onboardingDate; }
}
