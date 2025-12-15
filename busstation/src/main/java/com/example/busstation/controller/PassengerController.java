package com.example.busstation.controller;

import com.example.busstation.model.Passenger;
import com.example.busstation.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public String showPassengerList(
            Model model,
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        // 1. Luăm lista filtrată și sortată din Service
        model.addAttribute("passengers", passengerService.getAllPassengers(searchName, minAge, sortBy, sortDir));

        // 2. Punem parametrii înapoi în model pentru formularul de filtrare (Sticky Form)
        model.addAttribute("searchName", searchName);
        model.addAttribute("minAge", minAge);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        // 3. Calculăm sortarea inversă pentru click pe coloane
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "passenger/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passenger/form";
    }

    @PostMapping
    public String createPassenger(@Valid @ModelAttribute Passenger passenger, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "passenger/form";
        }
        passengerService.createPassenger(passenger);
        return "redirect:/passengers";
    }

    @PostMapping("/{id}/delete")
    public String deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return "redirect:/passengers";
    }

    @GetMapping("/{id}/details")
    public String showPassengerDetails(@PathVariable Long id, Model model) {
        model.addAttribute("passenger", passengerService.getPassengerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Passenger ID:" + id)));
        return "passenger/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.getPassengerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Passenger ID:" + id));
        model.addAttribute("passenger", passenger);
        return "passenger/edit_form";
    }

    @PostMapping("/{id}/update")
    public String updatePassenger(@PathVariable Long id, @Valid @ModelAttribute Passenger passenger, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            passenger.setId(id);
            return "passenger/edit_form";
        }
        passenger.setId(id);
        passengerService.createPassenger(passenger);
        return "redirect:/passengers";
    }
}