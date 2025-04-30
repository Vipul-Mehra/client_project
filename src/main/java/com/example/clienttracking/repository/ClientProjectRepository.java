package com.example.clienttracking.repository;

import com.example.clienttracking.model.ClientProjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProjectRepository extends JpaRepository<ClientProjects, Long> {

}
