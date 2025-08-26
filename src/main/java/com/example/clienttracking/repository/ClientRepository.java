package com.example.clienttracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppClientRepository extends JpaRepository<AppClient, Long> {
    // This method is crucial and needs to be present
    AppClient findByClientId(String clientId);
}