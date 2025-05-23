package com.example.clienttracking.service.impl;

import com.example.clienttracking.dto.TimeSheetDTO;
import com.example.clienttracking.model.ClientProjects;
import com.example.clienttracking.model.Resource;
import com.example.clienttracking.model.TimeSheets;
import com.example.clienttracking.repository.ClientProjectRepository;
import com.example.clienttracking.repository.ResourceRepository;
import com.example.clienttracking.repository.TimeSheetsRepository;
import com.example.clienttracking.service.TimeSheetsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSheetsServiceImpl implements TimeSheetsService {

    @Autowired
    private TimeSheetsRepository timeSheetsRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ClientProjectRepository clientProjectRepository;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // Map Entity to DTO
    private TimeSheetDTO mapToDTO(TimeSheets sheet) {
        TimeSheetDTO dto = new TimeSheetDTO();
        dto.setTimeSheetId(sheet.getTimeSheetId());

        if (sheet.getResource() != null) {
            dto.setResourceId(sheet.getResource().getResourceId());
        }

        if (sheet.getClientProject() != null) {
            dto.setClientProjectId(sheet.getClientProject().getClientProjectId());
        }

        dto.setWorkDate(sheet.getWorkDate() != null ? sdf.format(sheet.getWorkDate()) : null);
        dto.setHoursWorked(sheet.getHoursWorked());

        return dto;
    }

    // Map DTO to Entity
    private TimeSheets mapToEntity(TimeSheetDTO dto) {
        TimeSheets entity = new TimeSheets();
        entity.setTimeSheetId(dto.getTimeSheetId());

        if (dto.getResourceId() != null) {
            Resource resource = resourceRepository.findById(dto.getResourceId())
                    .orElseThrow(() -> new EntityNotFoundException("Resource not found"));
            entity.setResource(resource);
        } else {
            entity.setResource(null);
        }

        if (dto.getClientProjectId() != null) {
            ClientProjects project = clientProjectRepository.findById(dto.getClientProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Client Project not found"));
            entity.setClientProject(project);
        } else {
            entity.setClientProject(null);
        }

        try {
            if (dto.getWorkDate() != null && !dto.getWorkDate().trim().isEmpty()) {
                entity.setWorkDate(sdf.parse(dto.getWorkDate()));
            } else {
                entity.setWorkDate(null);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format: " + dto.getWorkDate());
        }

        entity.setHoursWorked(dto.getHoursWorked());
        return entity;
    }

    @Override
    public List<TimeSheetDTO> getAllWorkTimetables() {
        return timeSheetsRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSheetDTO getWorkTimetableById(Long id) {
        return timeSheetsRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("TimeSheet not found with ID: " + id));
    }

    @Override
    public TimeSheetDTO createWorkTimetable(TimeSheetDTO dto) {
        TimeSheets entity = mapToEntity(dto);
        return mapToDTO(timeSheetsRepository.save(entity));
    }

    @Override
    public TimeSheetDTO updateWorkTimetable(Long id, TimeSheetDTO dto) {
        TimeSheets existing = timeSheetsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TimeSheet not found"));

        if (dto.getResourceId() != null) {
            Resource resource = resourceRepository.findById(dto.getResourceId())
                    .orElseThrow(() -> new EntityNotFoundException("Resource not found"));
            existing.setResource(resource);
        } else {
            existing.setResource(null);
        }

        if (dto.getClientProjectId() != null) {
            ClientProjects project = clientProjectRepository.findById(dto.getClientProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Client Project not found"));
            existing.setClientProject(project);
        } else {
            existing.setClientProject(null);
        }

        try {
            if (dto.getWorkDate() != null && !dto.getWorkDate().trim().isEmpty()) {
                existing.setWorkDate(sdf.parse(dto.getWorkDate()));
            } else {
                existing.setWorkDate(null);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format");
        }

        existing.setHoursWorked(dto.getHoursWorked());

        return mapToDTO(timeSheetsRepository.save(existing));
    }

    @Override
    public void deleteWorkTimetable(Long id) {
        timeSheetsRepository.deleteById(id);
    }

    @Override
    public List<TimeSheetDTO> search(String keyword, LocalDate startDate, LocalDate endDate, LocalDate entityDate) {
        List<TimeSheets> allData = timeSheetsRepository.findAll();

        return allData.stream()
                .filter(item -> {
                    boolean matchKeyword = true;

                    if (keyword != null && !keyword.isEmpty()) {
                        String lower = keyword.toLowerCase();
                        matchKeyword = false;

                        // Check Client Name
                        if (item.getClientProject() != null &&
                                item.getClientProject().getClient() != null &&
                                item.getClientProject().getClient().getClientName() != null &&
                                item.getClientProject().getClient().getClientName().toLowerCase().contains(lower)) {
                            matchKeyword = true;
                        }

                        // Check Project Name
                        if (!matchKeyword && item.getClientProject() != null &&
                                item.getClientProject().getProject() != null &&
                                item.getClientProject().getProject().getProjectName() != null &&
                                item.getClientProject().getProject().getProjectName().toLowerCase().contains(lower)) {
                            matchKeyword = true;
                        }

                        // Check Resource Name
                        if (!matchKeyword && item.getResource() != null &&
                                item.getResource().getResourceName() != null &&
                                item.getResource().getResourceName().toLowerCase().contains(lower)) {
                            matchKeyword = true;
                        }
                    }

                    boolean matchDateRange = true;
                    boolean matchEntityDate = true;

                    // Convert workDate to LocalDate
                    LocalDate workDate = item.getWorkDate() != null
                            ? item.getWorkDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                            : null;

                    // Filter by date range
                    if (startDate != null || endDate != null) {
                        if (workDate == null) {
                            matchDateRange = false;
                        } else {
                            if (startDate != null && endDate != null) {
                                matchDateRange = !workDate.isBefore(startDate) && !workDate.isAfter(endDate);
                            } else if (startDate != null) {
                                matchDateRange = !workDate.isBefore(startDate);
                            } else {
                                matchDateRange = !workDate.isAfter(endDate);
                            }
                        }
                    }

                   
                    return matchKeyword && matchDateRange && matchEntityDate;
                })
                .map(this::mapToDTO).collect(Collectors.toList());
    }
}