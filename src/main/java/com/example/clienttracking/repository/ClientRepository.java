package com.example.clienttracking.repository;

import com.example.clienttracking.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Clients, Long> {
}
