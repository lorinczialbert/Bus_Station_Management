package com.example.busstation.service;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.model.enums.DriverRole;
import com.example.busstation.repository.DutyAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyAssignmentService {

    private final DutyAssignmentRepository dutyAssignmentRepository;

    @Autowired
    public DutyAssignmentService(DutyAssignmentRepository dutyAssignmentRepository) {
        this.dutyAssignmentRepository = dutyAssignmentRepository;
    }

    // --- FIX: Metoda simplă pentru compatibilitate ---
    public List<DutyAssignment> getAllAssignments() {
        return getAllAssignments(null, null, "id", "asc");
    }

    // --- Metoda complexă (Filtrare & Sortare) ---
    public List<DutyAssignment> getAllAssignments(DriverRole role, String staffName, String sortBy, String sortDir) {

        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        return dutyAssignmentRepository.searchAssignments(role, staffName, sort);
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