package com.example.busstation.controller;

import com.example.busstation.model.BusStation;
import com.example.busstation.service.BusStationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/busstations")
public class BusStationController {

    private final BusStationService busStationService;

    public BusStationController(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    /**
     * Afișează LISTA tuturor stațiilor.
     * Mapat la: GET /busstations
     */
    @GetMapping
    public String showBusStationList(
            Model model,
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) String searchCity,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        model.addAttribute("busStations", busStationService.getAllBusStations(searchName, searchCity, sortBy, sortDir));

        // Sticky fields
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchCity", searchCity);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "busstation/index";
    }

    /**
     * Afișează FORMULARUL pentru crearea unei noi stații.
     * Mapat la: GET /busstations/new
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("busStation", new BusStation());
        return "busstation/form";
    }

    /**
     * Procesează FORMULARUL de creare.
     * Mapat la: POST /busstations
     */
    @PostMapping
    public String createBusStation(@Valid @ModelAttribute("busStation") BusStation busStation, BindingResult bindingResult, Model model) {
        // 1. Verificăm validările (ex: Nume gol, Oraș gol)
        if (bindingResult.hasErrors()) {
            return "busstation/form";
        }

        // 2. Salvăm
        busStationService.createBusStation(busStation);
        return "redirect:/busstations";
    }

    /**
     * Șterge o stație.
     * Mapat la: POST /busstations/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteBusStation(@PathVariable Long id) {
        busStationService.deleteBusStation(id);
        return "redirect:/busstations";
    }

    /**
     * Afișează pagina de DETALII.
     * Mapat la: GET /busstations/{id}/details
     */
    @GetMapping("/{id}/details")
    public String showBusStationDetails(@PathVariable Long id, Model model) {
        model.addAttribute("busStation", busStationService.getBusStationById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID BusStation invalid: " + id)));
        return "busstation/details";
    }

    /**
     * Afișează FORMULARUL DE MODIFICARE.
     * Mapat la: GET /busstations/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        BusStation station = busStationService.getBusStationById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID BusStation invalid: " + id));
        model.addAttribute("busStation", station);
        return "busstation/edit_form";
    }

    /**
     * Procesează FORMULARUL DE MODIFICARE.
     * Mapat la: POST /busstations/{id}/update
     */
    @PostMapping("/{id}/update")
    public String updateBusStation(@PathVariable Long id, @Valid @ModelAttribute("busStation") BusStation busStation, BindingResult bindingResult, Model model) {
        // 1. Verificăm validările
        if (bindingResult.hasErrors()) {
            busStation.setId(id); // Setăm ID-ul pentru a nu-l pierde în formular
            return "busstation/edit_form";
        }

        // 2. Setăm ID-ul și facem update
        busStation.setId(id);
        busStationService.createBusStation(busStation);
        return "redirect:/busstations";
    }
}