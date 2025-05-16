package com.example.clienttracking.service.impl;

import com.example.clienttracking.dto.TimeSheetDTO;
import com.example.clienttracking.model.ClientProjects;
import com.example.clienttracking.model.Clients;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//pagination
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


@Service
public class TimeSheetsServiceImpl implements TimeSheetsService {

    @Autowired
    private TimeSheetsRepository timeSheetsRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ClientProjectRepository clientProjectRepository;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private TimeSheetDTO mapToDTO(TimeSheets sheet) {
        TimeSheetDTO dto = new TimeSheetDTO();
        dto.setTimeSheetId(sheet.getTimeSheetId());

        if (sheet.getResource() != null) {
            Resource resource = sheet.getResource();
            dto.setResourceId(resource.getResourceId());
//            dto.setResourceName(resource.getResourceName());
//            dto.setResourceRole(resource.getResourceRole());
        }

        if (sheet.getClientProject() != null) {
            ClientProjects clientProj = sheet.getClientProject();
            dto.setClientProjectId(clientProj.getClientProjectId());

            if (clientProj.getClient() != null) {
//                dto.setClientName(clientProj.getClient().getClientName());
            }

            if (clientProj.getProject() != null) {
//                dto.setProjectName(clientProj.getProject().getProjectName());
            }
        }

        dto.setWorkDate(sheet.getWorkDate() != null ? sdf.format(sheet.getWorkDate()) : null);
        dto.setHoursWorked(sheet.getHoursWorked());

        return dto;
    }
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

        // Update Resource
        if (dto.getResourceId() != null) {
            Resource resource = resourceRepository.findById(dto.getResourceId())
                    .orElseThrow(() -> new EntityNotFoundException("Resource not found"));
            existing.setResource(resource);
        } else {
            existing.setResource(null);
        }

        // Update ClientProject
        if (dto.getClientProjectId() != null) {
            ClientProjects project = clientProjectRepository.findById(dto.getClientProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Client Project not found"));
            existing.setClientProject(project);
        } else {
            existing.setClientProject(null);
        }
        // Update Work Date
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

    //pagination code here
    @Override
    public Page<TimeSheetDTO> getPaginatedTimeSheets(int page, int size) {
        Page<TimeSheets> timeSheetsPage = timeSheetsRepository.findAll(PageRequest.of(page, size));

        return timeSheetsPage.map(this::convertToDTO);
    }

    private TimeSheetDTO convertToDTO(TimeSheets entity) {
        TimeSheetDTO dto = new TimeSheetDTO();
        dto.setTimeSheetId(entity.getTimeSheetId());
        dto.setWorkDate(entity.getWorkDate() != null ? entity.getWorkDate().toString() : null);
        dto.setHoursWorked(entity.getHoursWorked());
        dto.setResourceId(entity.getResource().getResourceId());
        dto.setClientProjectId(entity.getClientProject().getClientProjectId());
        return dto;
    }



}
