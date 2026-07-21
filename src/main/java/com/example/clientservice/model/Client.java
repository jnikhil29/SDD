package com.example.clientservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String coreClientId;

    @Column(nullable = false, unique = true)
    private String clientName;

    @Column(nullable = false)
    private LocalDate onboardingDate;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    public Client() {
    }

    public Client(String coreClientId, String clientName, LocalDate onboardingDate) {
        this.coreClientId = coreClientId;
        this.clientName = clientName;
        this.onboardingDate = onboardingDate;
    }

    public Long getId() { return id; }
    public String getCoreClientId() { return coreClientId; }
    public void setCoreClientId(String coreClientId) { this.coreClientId = coreClientId; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public LocalDate getOnboardingDate() { return onboardingDate; }
    public void setOnboardingDate(LocalDate onboardingDate) { this.onboardingDate = onboardingDate; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
