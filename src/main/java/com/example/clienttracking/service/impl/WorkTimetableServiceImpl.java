package com.example.clienttracking.service.impl;

import com.example.clienttracking.model.WorkTimetable;
import com.example.clienttracking.repository.WorkTimetableRepository;
import com.example.clienttracking.service.WorkTimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTimetableServiceImpl implements WorkTimetableService {

    @Autowired
    private WorkTimetableRepository workTimetableRepository;

    @Override
    public List<WorkTimetable> getAllWorkTimetables() {
        return workTimetableRepository.findAll();
    }

    @Override
    public WorkTimetable getWorkTimetableById(Long id) {
        return workTimetableRepository.findById(id).orElse(null);
    }

    @Override
    public WorkTimetable createWorkTimetable(WorkTimetable workTimetable) {
        return workTimetableRepository.save(workTimetable);
    }

    @Override
    public WorkTimetable updateWorkTimetable(Long id, WorkTimetable workTimetableDetails) {
        WorkTimetable workTimetable = workTimetableRepository.findById(id).orElse(null);
        if (workTimetable != null) {
            workTimetable.setClient(workTimetableDetails.getClient());
            workTimetable.setResource(workTimetableDetails.getResource());
            workTimetable.setWorkDate(workTimetableDetails.getWorkDate());
            workTimetable.setHoursWorked(workTimetableDetails.getHoursWorked());
            return workTimetableRepository.save(workTimetable);
        }
        return null;
    }

    @Override
    public void deleteWorkTimetable(Long id) {
        workTimetableRepository.deleteById(id);
    }
}
