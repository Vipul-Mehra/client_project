// src/main/java/com/example/clienttracking/repository/UserRepository.java
package com.example.clienttracking.repository;

import com.example.clienttracking.model.keycloak; // Manages the User entity
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<keycloak, Long> { // Correctly uses User
    Optional<keycloak> findByUsername(String username);
    List<keycloak> findByUsernameContainingIgnoreCase(String username);

}