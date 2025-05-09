// src/main/java/com/example/clienttracking/repository/ClientProjectRepository.java
package com.example.clienttracking.repository;

import com.example.clienttracking.model.ClientProjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientProjectRepository extends JpaRepository<ClientProjects   , Long> {

    Optional<List<ClientProjects>> findByClient_Id(Long clientId);

}