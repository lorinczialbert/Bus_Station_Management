package com.example.busstation.controller;

import com.example.busstation.model.Passenger;
import com.example.busstation.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // WICHTIG: Nicht @RestController
@RequestMapping("/passengers") // URL-Pfad ohne /api
public class PassengerController extends AbstractBaseController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    /**
     * Zeigt die LISTE aller Passagiere an.
     * Mapped auf: GET /passengers
     */
    @GetMapping
    public String showPassengerList(Model model) {
        model.addAttribute("passengers", passengerService.getAllPassengers());
        // Sucht nach /resources/templates/passenger/index.html
        return "passenger/index";
    }

    /**
     * Zeigt das FORMULAR zum Erstellen eines neuen Passagiers an.
     * Mapped auf: GET /passengers/new
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        // Sucht nach /resources/templates/passenger/form.html
        return "passenger/form";
    }

    /**
     * Verarbeitet das FORMULAR (erstellt den Passagier).
     * Mapped auf: POST /passengers
     */
    @PostMapping
    public String createPassenger(@ModelAttribute Passenger passenger) {
        passengerService.createPassenger(passenger);
        // Leitet zurück zur Liste (GET /passengers)
        return "redirect:/passengers";
    }

    /**
     * Löscht einen Passagier.
     * Mapped auf: POST /passengers/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deletePassenger(@PathVariable String id) {
        passengerService.deletePassenger(id);
        // Leitet zurück zur Liste (GET /passengers)
        return "redirect:/passengers";
    }
}
