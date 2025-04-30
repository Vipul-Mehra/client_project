package com.example.clienttracking.repository;

import com.example.clienttracking.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTableRepository extends JpaRepository<Projects, Long> {

}