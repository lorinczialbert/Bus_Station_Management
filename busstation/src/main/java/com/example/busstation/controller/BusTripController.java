package com.example.busstation.controller;

import com.example.busstation.model.BusTrip;
import com.example.busstation.service.BusTripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // WICHTIG: Geändert von @RestController
@RequestMapping("/bustrips") // Pfad geändert (ohne /api)
public class BusTripController extends AbstractBaseController {

    private final BusTripService busTripService;

    public BusTripController(BusTripService busTripService) {
        this.busTripService = busTripService;
    }

    /**
     * Zeigt die LISTE aller BusTrips an.
     * Mapped auf: GET /bustrips
     */
    @GetMapping
    public String showBusTripList(Model model) {
        model.addAttribute("busTrips", busTripService.getAllBusTrips());
        // Sucht nach /resources/templates/bustrip/index.html
        return "bustrip/index";
    }

    /**
     * HINWEIS: GET /new und POST / werden weggelassen.
     * Das Formular ist komplex, da es Dropdowns für 'Bus' und 'Route' benötigt.
     */

    /**
     * Löscht einen BusTrip.
     * Mapped auf: POST /bustrips/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteBusTrip(@PathVariable String id) {
        busTripService.deleteBusTrip(id);
        // Leitet zurück zur Liste (GET /bustrips)
        return "redirect:/bustrips";
    }
}
