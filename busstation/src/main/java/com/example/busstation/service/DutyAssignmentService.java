package com.example.busstation.service;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.repository.IDutyAssignmentRepository; // MODIFICAT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyAssignmentService {

    private final IDutyAssignmentRepository dutyAssignmentRepository; // MODIFICAT

    @Autowired
    public DutyAssignmentService(IDutyAssignmentRepository dutyAssignmentRepository) { // MODIFICAT
        this.dutyAssignmentRepository = dutyAssignmentRepository;
    }

    public List<DutyAssignment> getAllAssignments() {
        return dutyAssignmentRepository.findAll();
    }

    public Optional<DutyAssignment> getAssignmentById(String id) {
        return dutyAssignmentRepository.findById(id);
    }

    public DutyAssignment createAssignment(DutyAssignment assignment) {
        return dutyAssignmentRepository.save(assignment);
    }

    public void deleteAssignment(String id) {
        dutyAssignmentRepository.delete(id);
    }
}