package com.example.busstation.controller;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.service.DutyAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
public class DutyAssignmentController extends AbstractBaseController {

    private final DutyAssignmentService dutyAssignmentService;

    public DutyAssignmentController(DutyAssignmentService dutyAssignmentService) {
        this.dutyAssignmentService = dutyAssignmentService;
    }

    @GetMapping
    public List<DutyAssignment> getAllAssignments() {
        return dutyAssignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DutyAssignment> getAssignmentById(@PathVariable String id) {
        Optional<DutyAssignment> assignment = dutyAssignmentService.getAssignmentById(id);
        return assignment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DutyAssignment createAssignment(@RequestBody DutyAssignment assignment) {
        return dutyAssignmentService.createAssignment(assignment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAssignment(@PathVariable String id) {
        dutyAssignmentService.deleteAssignment(id);
    }
}
