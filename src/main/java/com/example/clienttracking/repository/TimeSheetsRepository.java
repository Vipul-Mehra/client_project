// com.example.clienttracking.repository.TimeSheetsRepository.java
package com.example.clienttracking.repository;

import com.example.clienttracking.model.TimeSheets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeSheetsRepository extends JpaRepository<TimeSheets, Long> {

}
