package com.example.busstation.repository;

import com.example.busstation.model.DutyAssignment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DutyAssignmentRepository implements IDutyAssignmentRepository {

    private final List<DutyAssignment> assignments = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<DutyAssignment> findAll() {
        return assignments;
    }

    @Override
    public Optional<DutyAssignment> findById(String id) {
        for (DutyAssignment assignment : assignments) {
            if (assignment.getId().equals(id)) {
                return Optional.of(assignment);
            }
        }
        return Optional.empty();
    }

    @Override
    public DutyAssignment save(DutyAssignment assignment) {
        if (assignment.getId() == null || assignment.getId().isEmpty()) {
            String newId = String.valueOf(nextId++);
            assignment.setId(newId);
            assignments.add(assignment);
        } else {
            Optional<DutyAssignment> existingOpt = findById(assignment.getId());
            if (existingOpt.isPresent()) {
                assignments.remove(existingOpt.get());
            }
            assignments.add(assignment);
        }
        return assignment;
    }

    @Override
    public void delete(String id) {
        assignments.removeIf(assignment -> assignment.getId().equals(id));
    }
}