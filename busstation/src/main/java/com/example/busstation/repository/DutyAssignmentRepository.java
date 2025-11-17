package com.example.busstation.repository;

import com.example.busstation.model.DutyAssignment;
import org.springframework.stereotype.Repository;

@Repository
public class DutyAssignmentRepository extends InFileRepository<DutyAssignment, String> implements IDutyAssignmentRepository {

    public DutyAssignmentRepository() {
        super("data/assignments.json", DutyAssignment[].class);
    }
}