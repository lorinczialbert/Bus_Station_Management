package com.example.busstation.controller;

import com.example.busstation.model.Driver;
import com.example.busstation.model.Staff;
import com.example.busstation.model.TripManager;
import com.example.busstation.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public String showStaffList(Model model) {
        model.addAttribute("staffMembers", staffService.getAllStaff());
        return "staff/index";
    }

    // Creating new staff (GET/POST /new) is omitted as per your original file,
    // likely handled by separate controllers or not implemented yet due to inheritance complexity.

    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }

    @GetMapping("/{id}/details")
    public String showStaffDetails(@PathVariable Long id, Model model) {
        model.addAttribute("staffMember", staffService.getStaffById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Staff ID:" + id)));
        return "staff/details";
    }

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

    // UPDATE DRIVER
    @PostMapping("/driver/{id}/update")
    public String updateDriver(@PathVariable Long id, @Valid @ModelAttribute Driver driver, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            driver.setId(id);
            return "staff/edit_driver_form"; // Return to the driver edit form on error
        }
        driver.setId(id);
        staffService.createStaff(driver);
        return "redirect:/staff";
    }

    // UPDATE MANAGER
    @PostMapping("/manager/{id}/update")
    public String updateManager(@PathVariable Long id, @Valid @ModelAttribute TripManager manager, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            manager.setId(id);
            return "staff/edit_manager_form"; // Return to the manager edit form on error
        }
        manager.setId(id);
        staffService.createStaff(manager);
        return "redirect:/staff";
    }
}