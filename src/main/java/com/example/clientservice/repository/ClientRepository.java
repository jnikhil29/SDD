package com.example.clientservice.repository;

import com.example.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCoreClientId(String coreClientId);
    boolean existsByCoreClientId(String coreClientId);
    boolean existsByClientName(String clientName);
}
