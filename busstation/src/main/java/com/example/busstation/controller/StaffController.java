package com.example.busstation.controller;

import com.example.busstation.model.Staff;
import com.example.busstation.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // WICHTIG: Geändert von @RestController
@RequestMapping("/staff") // Pfad geändert (ohne /api)
public class StaffController extends AbstractBaseController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * Zeigt die LISTE aller Mitarbeiter an.
     * Mapped auf: GET /staff
     */
    @GetMapping
    public String showStaffList(Model model) {
        model.addAttribute("staffMembers", staffService.getAllStaff());
        // Sucht nach /resources/templates/staff/index.html
        return "staff/index";
    }

    /**
     * HINWEIS: GET /new und POST / werden weggelassen.
     * Das Formular ist komplex, da Staff 'abstract' ist und man
     * stattdessen 'Driver' oder 'TripManager' erstellen müsste.
     * Das ist mehr als ein "einfaches Formular" (P2).
     */

    /**
     * Löscht einen Mitarbeiter.
     * Mapped auf: POST /staff/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id) {
        staffService.deleteStaff(id);
        // Leitet zurück zur Liste (GET /staff)
        return "redirect:/staff";
    }
}
