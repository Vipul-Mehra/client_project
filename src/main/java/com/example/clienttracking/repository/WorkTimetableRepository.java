package com.example.clienttracking.repository;

import com.example.clienttracking.model.WorkTimetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimetableRepository extends JpaRepository<WorkTimetable, Long> {
}
