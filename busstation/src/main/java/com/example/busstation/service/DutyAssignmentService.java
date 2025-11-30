package com.example.busstation.service;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.repository.DutyAssignmentRepository; // MODIFICAT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyAssignmentService {

    private final DutyAssignmentRepository dutyAssignmentRepository; // MODIFICAT

    @Autowired
    public DutyAssignmentService(DutyAssignmentRepository dutyAssignmentRepository) { // MODIFICAT
        this.dutyAssignmentRepository = dutyAssignmentRepository;
    }

    public List<DutyAssignment> getAllAssignments() {
        return dutyAssignmentRepository.findAll();
    }

    public Optional<DutyAssignment> getAssignmentById(Long id) {
        return dutyAssignmentRepository.findById(id);
    }

    public DutyAssignment createAssignment(DutyAssignment assignment) {
        return dutyAssignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        dutyAssignmentRepository.deleteById(id);
    }
}