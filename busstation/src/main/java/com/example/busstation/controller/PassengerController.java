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

    /**
     * NOU: Afișează pagina de DETALII pentru un singur pasager.
     * Mapat la: GET /passengers/{id}/details
     */
    @GetMapping("/{id}/details")
    public String showPassengerDetails(@PathVariable String id, Model model) {
        model.addAttribute("passenger", passengerService.getPassengerById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Pasager invalid:" + id)));
        return "passenger/details";
    }

    /**
     * NOU: Afișează FORMULARUL DE MODIFICARE (Editare).
     * Mapat la: GET /passengers/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Passenger passenger = passengerService.getPassengerById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Pasager invalid:" + id));
        model.addAttribute("passenger", passenger);
        return "passenger/edit_form";
    }

    /**
     * NOU: Procesează FORMULARUL DE MODIFICARE.
     * Mapat la: POST /passengers/{id}/update
     */
    @PostMapping("/{id}/update")
    public String updatePassenger(@PathVariable String id, @ModelAttribute Passenger passenger) {
        passenger.setId(id); // Asigură-te că ID-ul este setat corect
        passengerService.createPassenger(passenger); // Metoda 'save' se ocupă și de update
        return "redirect:/passengers";
    }
}
