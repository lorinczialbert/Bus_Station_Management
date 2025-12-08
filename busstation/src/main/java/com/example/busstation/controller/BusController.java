package com.example.busstation.controller;

import com.example.busstation.model.Bus;

import com.example.busstation.service.BusService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import org.springframework.dao.DataIntegrityViolationException;

@Controller

@RequestMapping("/buses")

public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {

        this.busService = busService;

    }

    /**

     * Afișează LISTA tuturor autobuzelor.

     * Mapat la: GET /buses

     */

    @GetMapping

    public String showBusList(Model model) {

        model.addAttribute("buses", busService.getAllBusse());

        return "bus/index";

    }

    /**

     * Afișează FORMULARUL pentru crearea unui nou autobuz.

     * Mapat la: GET /buses/new

     */

    @GetMapping("/new")

    public String showCreateForm(Model model) {

        model.addAttribute("bus", new Bus());

        return "bus/form";

    }

    /**

     * Procesează FORMULARUL de creare (Salvare).

     * Mapat la: POST /buses

     * Include VALIDARE.

     */

    @PostMapping
    public String createBus(@Valid @ModelAttribute("bus") Bus bus, BindingResult bindingResult, Model model) {
        // 1. Standard Validation (Empty fields, negative numbers)
        if (bindingResult.hasErrors()) {
            return "bus/form";
        }

        try {
            // 2. Try to save to the database
            busService.createBus(bus);
        } catch (DataIntegrityViolationException e) {
            // 3. CATCH THE DUPLICATE ERROR
            // This happens if the unique constraint is violated
            bindingResult.rejectValue("name", "error.bus", "Dieses Kennzeichen existiert bereits!");
            return "bus/form";
        }

        return "redirect:/buses";
    }

    /**

     * Șterge un autobuz.

     * Mapat la: POST /buses/{id}/delete

     */

    @PostMapping("/{id}/delete")

    public String deleteBus(@PathVariable Long id) {

        busService.deleteBus(id);

        return "redirect:/buses";

    }

    /**

     * Afișează pagina de DETALII.

     * Mapat la: GET /buses/{id}/details

     */

    @GetMapping("/{id}/details")

    public String showBusDetails(@PathVariable Long id, Model model) {

        Bus bus = busService.getBusById(id)

                .orElseThrow(() -> new IllegalArgumentException("ID Autobuz invalid: " + id));

        model.addAttribute("bus", bus);

        return "bus/details";

    }

    /**

     * Afișează FORMULARUL DE MODIFICARE (Editare).

     * Mapat la: GET /buses/{id}/edit

     */

    @GetMapping("/{id}/edit")

    public String showEditForm(@PathVariable Long id, Model model) {

        Bus bus = busService.getBusById(id)

                .orElseThrow(() -> new IllegalArgumentException("ID Autobuz invalid: " + id));

        model.addAttribute("bus", bus);

        return "bus/edit_form";

    }

    /**

     * Procesează FORMULARUL DE MODIFICARE (Update).

     * Mapat la: POST /buses/{id}/update

     * Include VALIDARE.

     */

    @PostMapping("/{id}/update")

    public String updateBus(@PathVariable Long id, @Valid @ModelAttribute("bus") Bus bus, BindingResult bindingResult, Model model) {

        // 1. Verificăm erorile de validare

        if (bindingResult.hasErrors()) {

            // Dacă sunt erori, rămânem pe pagina de editare

            // Este important ca ID-ul să fie setat pentru ca formularul să știe ce edităm

            bus.setId(id);

            return "bus/edit_form";

        }

        // 2. Setăm ID-ul corect pentru update și salvăm

        bus.setId(id);

        busService.createBus(bus);

        return "redirect:/buses";

    }

}
 