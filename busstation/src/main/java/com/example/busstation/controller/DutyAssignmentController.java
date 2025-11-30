package com.example.busstation.controller;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.service.BusTripService;
import com.example.busstation.service.DutyAssignmentService;
import com.example.busstation.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
public class DutyAssignmentController {

    private final DutyAssignmentService dutyAssignmentService;
    private final BusTripService busTripService;
    private final StaffService staffService;

    public DutyAssignmentController(DutyAssignmentService dutyAssignmentService, BusTripService busTripService, StaffService staffService) {
        this.dutyAssignmentService = dutyAssignmentService;
        this.busTripService = busTripService;
        this.staffService = staffService;
    }

    @GetMapping
    public String showAssignmentList(Model model) {
        model.addAttribute("assignments", dutyAssignmentService.getAllAssignments());
        return "dutyassignment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new DutyAssignment());
        // Load dependencies
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allStaffMembers", staffService.getAllStaff());
        return "dutyassignment/form";
    }

    @PostMapping
    public String createAssignment(@Valid @ModelAttribute DutyAssignment assignment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Reload dependencies on error
            model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
            model.addAttribute("allStaffMembers", staffService.getAllStaff());
            return "dutyassignment/form";
        }
        dutyAssignmentService.createAssignment(assignment);
        return "redirect:/assignments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable Long id) {
        dutyAssignmentService.deleteAssignment(id);
        return "redirect:/assignments";
    }

    @GetMapping("/{id}/details")
    public String showAssignmentDetails(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", dutyAssignmentService.getAssignmentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Assignment ID:" + id)));
        return "dutyassignment/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        DutyAssignment assignment = dutyAssignmentService.getAssignmentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Assignment ID:" + id));
        model.addAttribute("assignment", assignment);

        // Load dependencies
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allStaffMembers", staffService.getAllStaff());
        return "dutyassignment/edit_form";
    }

    @PostMapping("/{id}/update")
    public String updateAssignment(@PathVariable Long id, @Valid @ModelAttribute DutyAssignment assignment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            assignment.setId(id);
            // Reload dependencies on error
            model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
            model.addAttribute("allStaffMembers", staffService.getAllStaff());
            return "dutyassignment/edit_form";
        }
        assignment.setId(id);
        dutyAssignmentService.createAssignment(assignment);
        return "redirect:/assignments";
    }
}