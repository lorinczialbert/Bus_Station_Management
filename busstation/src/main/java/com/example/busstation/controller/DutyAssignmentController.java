package com.example.busstation.controller;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.service.BusTripService;  // <-- *IMPORT HINZUFÜGEN*
import com.example.busstation.service.DutyAssignmentService;
import com.example.busstation.service.StaffService;    // <-- *IMPORT HINZUFÜGEN*
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
public class DutyAssignmentController extends AbstractBaseController {

    private final DutyAssignmentService dutyAssignmentService;
    private final BusTripService busTripService; // <-- *SERVICE HINZUFÜGEN*
    private final StaffService staffService;     // <-- *SERVICE HINZUFÜGEN*

    // Konstruktor anpassen
    public DutyAssignmentController(DutyAssignmentService dutyAssignmentService, BusTripService busTripService, StaffService staffService) {
        this.dutyAssignmentService = dutyAssignmentService;
        this.busTripService = busTripService;     // <-- *SERVICE HINZUFÜGEN*
        this.staffService = staffService;         // <-- *SERVICE HINZUFÜGEN*
    }

    /**
     * Zeigt die LISTE aller Zuweisungen an.
     * Mapped auf: GET /assignments
     */
    @GetMapping
    public String showAssignmentList(Model model) {
        model.addAttribute("assignments", dutyAssignmentService.getAllAssignments());
        return "dutyassignment/index";
    }

    // --- *NEUE METHODEN HINZUFÜGEN* ---

    /**
     * Zeigt das FORMULAR zum Erstellen einer neuen Zuweisung an.
     * Mapped auf: GET /assignments/new
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new DutyAssignment());

        // Listen für die Dropdowns
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allStaffMembers", staffService.getAllStaff());

        return "dutyassignment/form";
    }

    /**
     * Verarbeitet das FORMULAR (erstellt die Zuweisung).
     * Mapped auf: POST /assignments
     */
    @PostMapping
    public String createAssignment(@ModelAttribute DutyAssignment assignment) {
        dutyAssignmentService.createAssignment(assignment);
        return "redirect:/assignments";
    }

    // --- *ENDE NEUE METHODEN* ---

    /**
     * Löscht eine Zuweisung.
     * Mapped auf: POST /assignments/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable Long id) {
        dutyAssignmentService.deleteAssignment(id);
        return "redirect:/assignments";
    }

    /**
     * NOU: Afișează pagina de DETALII.
     * Mapat la: GET /assignments/{id}/details
     */
    @GetMapping("/{id}/details")
    public String showAssignmentDetails(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", dutyAssignmentService.getAssignmentById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Assignment invalid:" + id)));
        // Puteți adăuga services pentru a afișa numele staff/trip
        return "dutyassignment/details";
    }

    /**
     * NOU: Afișează FORMULARUL DE MODIFICARE (Editare).
     * Mapat la: GET /assignments/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        DutyAssignment assignment = dutyAssignmentService.getAssignmentById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Assignment invalid:" + id));
        model.addAttribute("assignment", assignment);

        // Listen für die Dropdowns
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allStaffMembers", staffService.getAllStaff());

        return "dutyassignment/edit_form";
    }

    /**
     * NOU: Procesează FORMULARUL DE MODIFICARE.
     * Mapat la: POST /assignments/{id}/update
     */
    @PostMapping("/{id}/update")
    public String updateAssignment(@PathVariable Long id, @ModelAttribute DutyAssignment assignment) {
        assignment.setId(id);
        dutyAssignmentService.createAssignment(assignment);
        return "redirect:/assignments";
    }
}