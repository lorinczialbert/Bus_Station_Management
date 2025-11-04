package com.example.busstation.controller;

import com.example.busstation.model.Driver;      // <-- *IMPORT HINZUFÜGEN*
import com.example.busstation.model.Staff;
import com.example.busstation.model.TripManager; // <-- *IMPORT HINZUFÜGEN*
import com.example.busstation.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
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
        return "staff/index";
    }

    // --- *NEUE METHODEN HINZUFÜGEN* ---

    /**
     * Zeigt das FORMULAR zum Erstellen eines neuen *Fahrers* (Driver).
     * Mapped auf: GET /staff/new/driver
     */
    @GetMapping("/new/driver")
    public String showCreateDriverForm(Model model) {
        model.addAttribute("driver", new Driver());
        return "staff/form-driver"; // Zeigt staff/form-driver.html
    }

    /**
     * Verarbeitet das FORMULAR (erstellt den Driver).
     * Mapped auf: POST /staff/driver
     */
    @PostMapping("/driver")
    public String createDriver(@ModelAttribute Driver driver) {
        staffService.createStaff(driver); // Speichert das Driver-Objekt
        return "redirect:/staff";
    }

    /**
     * Zeigt das FORMULAR zum Erstellen eines neuen *Managers* (TripManager).
     * Mapped auf: GET /staff/new/manager
     */
    @GetMapping("/new/manager")
    public String showCreateManagerForm(Model model) {
        model.addAttribute("manager", new TripManager());
        return "staff/form-manager"; // Zeigt staff/form-manager.html
    }

    /**
     * Verarbeitet das FORMULAR (erstellt den TripManager).
     * Mapped auf: POST /staff/manager
     */
    @PostMapping("/manager")
    public String createManager(@ModelAttribute TripManager manager) {
        staffService.createStaff(manager); // Speichert das Manager-Objekt
        return "redirect:/staff";
    }

    // --- *ENDE NEUE METHODEN* ---

    /**
     * Löscht einen Mitarbeiter.
     * Mapped auf: POST /staff/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }
}