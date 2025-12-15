package com.example.busstation.controller;

import com.example.busstation.model.Driver;
import com.example.busstation.model.Staff;
import com.example.busstation.model.TripManager;
import com.example.busstation.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // --- 1. LISTARE CU FILTRARE ȘI SORTARE (Nou) ---
    // Aici nu contează că Staff e abstract, Hibernate returnează subclasele
    @GetMapping
    public String showStaffList(
            Model model,
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        // Folosim metoda din Service care face filtrarea
        model.addAttribute("staffMembers", staffService.getAllStaff(searchName, sortBy, sortDir));

        // Sticky fields pentru formularul de căutare
        model.addAttribute("searchName", searchName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "staff/index";
    }

    // --- 2. CREARE DRIVER (Specific) ---
    @GetMapping("/driver/new")
    public String showCreateDriverForm(Model model) {
        model.addAttribute("driver", new Driver()); // Aici instanțiem concret Driver
        return "staff/create_driver"; // Asigură-te că ai acest template sau folosești unul comun
    }

    @PostMapping("/driver")
    public String createDriver(@Valid @ModelAttribute Driver driver, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "staff/create_driver";
        }
        staffService.createStaff(driver);
        return "redirect:/staff";
    }

    // --- 3. CREARE MANAGER (Specific - din codul tău) ---
    @GetMapping("/manager/new")
    public String showCreateManagerForm(Model model) {
        model.addAttribute("manager", new TripManager()); // Aici instanțiem concret TripManager
        return "staff/create_manager";
    }

    @PostMapping("/manager")
    public String createManager(@Valid @ModelAttribute TripManager manager, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "staff/create_manager";
        }
        staffService.createStaff(manager);
        return "redirect:/staff";
    }

    // --- 4. DELETE (Comun) ---
    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }

    // --- 5. DETAILS (Comun) ---
    @GetMapping("/{id}/details")
    public String showStaffDetails(@PathVariable Long id, Model model) {
        Staff staff = staffService.getStaffById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Staff ID:" + id));

        model.addAttribute("staffMember", staff);

        // Putem trimite un flag către view să știm ce tip este, dacă vrem să afișăm câmpuri specifice
        if (staff instanceof Driver) {
            model.addAttribute("type", "Driver");
        } else if (staff instanceof TripManager) {
            model.addAttribute("type", "Manager");
        }

        return "staff/details";
    }

    // --- 6. EDIT (Logica ta veche care detectează tipul) ---
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Staff staff = staffService.getStaffById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Staff ID:" + id));

        if (staff instanceof Driver) {
            model.addAttribute("driver", (Driver) staff);
            return "staff/edit_driver_form";
        } else if (staff instanceof TripManager) {
            model.addAttribute("manager", (TripManager) staff);
            return "staff/edit_manager_form";
        } else {
            return "redirect:/staff";
        }
    }

    @PostMapping("/driver/{id}/update")
    public String updateDriver(@PathVariable Long id, @Valid @ModelAttribute Driver driver, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            driver.setId(id);
            return "staff/edit_driver_form";
        }
        driver.setId(id);
        staffService.createStaff(driver);
        return "redirect:/staff";
    }

    @PostMapping("/manager/{id}/update")
    public String updateManager(@PathVariable Long id, @Valid @ModelAttribute TripManager manager, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            manager.setId(id);
            return "staff/edit_manager_form";
        }
        manager.setId(id);
        staffService.createStaff(manager);
        return "redirect:/staff";
    }
}