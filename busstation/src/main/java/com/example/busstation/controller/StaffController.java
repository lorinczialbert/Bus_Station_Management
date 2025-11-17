package com.example.busstation.controller;

import com.example.busstation.model.Staff;
import com.example.busstation.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.busstation.model.Driver;
import com.example.busstation.model.TripManager;

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

    /**
     * NOU: Afișează pagina de DETALII.
     * Mapat la: GET /staff/{id}/details
     */
    @GetMapping("/{id}/details")
    public String showStaffDetails(@PathVariable String id, Model model) {
        model.addAttribute("staffMember", staffService.getStaffById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Staff invalid:" + id)));
        return "staff/details";
    }

    /**
     * NOU: Afișează FORMULARUL DE MODIFICARE (Editare).
     * Mapat la: GET /staff/{id}/edit
     */
    /**
     * NOU: Afișează FORMULARUL DE MODIFICARE corect (Driver sau Manager).
     * Mapat la: GET /staff/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Staff staff = staffService.getStaffById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Staff invalid:" + id));

        // Verificăm tipul obiectului
        if (staff instanceof Driver) {
            model.addAttribute("driver", (Driver) staff); // Trimitem un obiect Driver
            return "staff/edit_driver_form"; // Returnăm formularul pentru Driver

        } else if (staff instanceof TripManager) {
            model.addAttribute("manager", (TripManager) staff); // Trimitem un obiect TripManager
            return "staff/edit_manager_form"; // Returnăm formularul pentru Manager

        } else {
            // Cazul în care tipul nu e cunoscut
            return "redirect:/staff";
        }
    }
    /**
     * NOU: Procesează FORMULARUL DE MODIFICARE PENTRU DRIVER.
     * Mapat la: POST /staff/driver/{id}/update
     */
    @PostMapping("/driver/{id}/update")
    public String updateDriver(@PathVariable String id, @ModelAttribute Driver driver) {
        driver.setId(id); // Asigură-te că ID-ul este setat corect
        staffService.createStaff(driver); // Metoda 'save' se ocupă și de update polimorfic
        return "redirect:/staff";
    }

    /**
     * NOU: Procesează FORMULARUL DE MODIFICARE PENTRU MANAGER.
     * Mapat la: POST /staff/manager/{id}/update
     */
    @PostMapping("/manager/{id}/update")
    public String updateManager(@PathVariable String id, @ModelAttribute TripManager manager) {
        manager.setId(id); // Asigură-te că ID-ul este setat corect
        staffService.createStaff(manager); // Metoda 'save' se ocupă și de update polimorfic
        return "redirect:/staff";
    }

}
