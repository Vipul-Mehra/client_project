package com.example.clienttracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.clienttracking.model.keycloak;

public interface keycloakRepository extends JpaRepository<keycloak, Long> {
  boolean existsByUserIdAndProductId(String userId, String productId);
}
