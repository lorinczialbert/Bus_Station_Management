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
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Staff staff = staffService.getStaffById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Staff invalid:" + id));

        // Trimitem obiectul generic "staff" la formular
        model.addAttribute("staff", staff);

        // TODO: Ideal ar fi să verificăm tipul (instanceof Driver/TripManager)
        // și să returnăm un formular specific.
        // Deocamdată, returnăm un formular generic "edit_form".
        return "staff/edit_form";
    }

    /**
     * NOU: Procesează FORMULARUL DE MODIFICARE.
     * Mapat la: POST /staff/{id}/update
     */
    @PostMapping("/{id}/update")
    public String updateStaff(@PathVariable String id, @ModelAttribute Staff staff) {
        // Aceasta este o simplificare. Salvarea polimorfică poate fi complexă.
        // 'staff' primit din formular poate fi incomplet.

        // O abordare mai sigură este să luăm obiectul existent și să actualizăm doar câmpurile comune
        Staff existingStaff = staffService.getStaffById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Staff invalid:" + id));

        existingStaff.setStaffName(staff.getStaffName());
        // TODO: Actualizați câmpurile specifice (Driver/Manager)

        staffService.createStaff(existingStaff);
        return "redirect:/staff";
    }
}
