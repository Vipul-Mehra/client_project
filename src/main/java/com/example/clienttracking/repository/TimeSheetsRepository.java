// com.example.clienttracking.repository.TimeSheetsRepository.java
package com.example.clienttracking.repository;

import com.example.clienttracking.model.TimeSheets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetsRepository extends JpaRepository<TimeSheets, Long> {
}