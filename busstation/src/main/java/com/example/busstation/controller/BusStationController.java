package com.example.busstation.controller;

import com.example.busstation.model.BusStation;
import com.example.busstation.service.BusStationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // WICHTIG: Geändert von @RestController
@RequestMapping("/busstations") // Pfad geändert (ohne /api)
public class BusStationController extends AbstractBaseController {

    private final BusStationService busStationService;

    public BusStationController(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    /**
     * Zeigt die LISTE aller BusStationen an.
     * Mapped auf: GET /busstations
     */
    @GetMapping
    public String showBusStationList(Model model) {
        model.addAttribute("busStations", busStationService.getAllBusStations());
        // Sucht nach /resources/templates/busstation/index.html
        return "busstation/index";
    }

    /**
     * Zeigt das FORMULAR zum Erstellen einer neuen BusStation an.
     * Mapped auf: GET /busstations/new
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("busStation", new BusStation());
        // Sucht nach /resources/templates/busstation/form.html
        return "busstation/form";
    }

    /**
     * Verarbeitet das FORMULAR (erstellt die BusStation).
     * Mapped auf: POST /busstations
     */
    @PostMapping
    public String createBusStation(@ModelAttribute BusStation busStation) {
        busStationService.createBusStation(busStation);
        // Leitet zurück zur Liste (GET /busstations)
        return "redirect:/busstations";
    }

    /**
     * Löscht eine BusStation.
     * Mapped auf: POST /busstations/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteBusStation(@PathVariable String id) {
        busStationService.deleteBusStation(id);
        // Leitet zurück zur Liste (GET /busstations)
        return "redirect:/busstations";
    }

    /**
     * NOU: Afișează pagina de DETALII.
     * Mapat la: GET /busstations/{id}/details
     */
    @GetMapping("/{id}/details")
    public String showBusStationDetails(@PathVariable String id, Model model) {
        model.addAttribute("busStation", busStationService.getBusStationById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID BusStation invalid:" + id)));
        return "busstation/details";
    }

    /**
     * NOU: Afișează FORMULARUL DE MODIFICARE (Editare).
     * Mapat la: GET /busstations/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        BusStation station = busStationService.getBusStationById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID BusStation invalid:" + id));
        model.addAttribute("busStation", station);
        return "busstation/edit_form";
    }

    /**
     * NOU: Procesează FORMULARUL DE MODIFICARE.
     * Mapat la: POST /busstations/{id}/update
     */
    @PostMapping("/{id}/update")
    public String updateBusStation(@PathVariable String id, @ModelAttribute BusStation busStation) {
        busStation.setId(id);
        busStationService.createBusStation(busStation);
        return "redirect:/busstations";
    }
}
