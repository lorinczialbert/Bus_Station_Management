package com.example.busstation.controller;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.model.enums.DriverRole;
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
    public String showAssignmentList(
            Model model,
            @RequestParam(required = false) DriverRole searchRole,
            @RequestParam(required = false) String searchStaffName,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        model.addAttribute("assignments", dutyAssignmentService.getAllAssignments(searchRole, searchStaffName, sortBy, sortDir));

        // Sticky Forms & Sort params
        model.addAttribute("searchRole", searchRole);
        model.addAttribute("searchStaffName", searchStaffName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "dutyassignment/index";
    }

    // ... restul metodelor (create, delete, details etc.) rămân neschimbate ...
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new DutyAssignment());
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allStaffMembers", staffService.getAllStaff());
        return "dutyassignment/form";
    }

    @PostMapping
    public String createAssignment(@Valid @ModelAttribute DutyAssignment assignment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
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
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allStaffMembers", staffService.getAllStaff());
        return "dutyassignment/edit_form";
    }

    @PostMapping("/{id}/update")
    public String updateAssignment(@PathVariable Long id, @Valid @ModelAttribute DutyAssignment assignment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            assignment.setId(id);
            model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
            model.addAttribute("allStaffMembers", staffService.getAllStaff());
            return "dutyassignment/edit_form";
        }
        assignment.setId(id);
        dutyAssignmentService.createAssignment(assignment);
        return "redirect:/assignments";
    }
}