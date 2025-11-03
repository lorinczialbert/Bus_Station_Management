package com.example.busstation.controller;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.service.DutyAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // WICHTIG: Geändert von @RestController
@RequestMapping("/assignments") // Pfad geändert (ohne /api)
public class DutyAssignmentController extends AbstractBaseController {

    private final DutyAssignmentService dutyAssignmentService;

    public DutyAssignmentController(DutyAssignmentService dutyAssignmentService) {
        this.dutyAssignmentService = dutyAssignmentService;
    }

    /**
     * Zeigt die LISTE aller Zuweisungen an.
     * Mapped auf: GET /assignments
     */
    @GetMapping
    public String showAssignmentList(Model model) {
        model.addAttribute("assignments", dutyAssignmentService.getAllAssignments());
        // Sucht nach /resources/templates/dutyassignment/index.html
        return "dutyassignment/index";
    }

    /**
     * HINWEIS: GET /new und POST / werden weggelassen.
     * Das Formular ist komplex, da es Dropdowns für 'BusTrip' und 'Staff' benötigt.
     */

    /**
     * Löscht eine Zuweisung.
     * Mapped auf: POST /assignments/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable String id) {
        dutyAssignmentService.deleteAssignment(id);
        // Leitet zurück zur Liste (GET /assignments)
        return "redirect:/assignments";
    }
}
