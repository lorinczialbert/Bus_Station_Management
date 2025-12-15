package com.example.busstation.controller;

import com.example.busstation.model.Bus;
import com.example.busstation.model.enums.BusStatus; // Import necesar pentru Enum
import com.example.busstation.service.BusService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    /**
     * Afișează LISTA tuturor autobuzelor cu opțiuni de FILTRARE și SORTARE.
     * Mapat la: GET /buses
     */
    @GetMapping
    public String showBusList(
            Model model,
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) BusStatus searchStatus,
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        // 1. Apelăm Service-ul cu parametrii de filtrare și sortare
        model.addAttribute("buses", busService.getAllBusse(searchName, searchStatus, minCapacity, sortBy, sortDir));

        // 2. Punem parametrii înapoi în Model pentru a-i afișa în formular (Sticky Form)
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchStatus", searchStatus);
        model.addAttribute("minCapacity", minCapacity);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        // 3. Calculăm direcția inversă pentru link-urile din tabel (dacă e asc, devine desc)
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

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
     */
    @PostMapping
    public String createBus(@Valid @ModelAttribute("bus") Bus bus, BindingResult bindingResult, Model model) {
        // 1. Validare standard
        if (bindingResult.hasErrors()) {
            return "bus/form";
        }

        try {
            // 2. Încercăm salvarea
            busService.createBus(bus);
        } catch (DataIntegrityViolationException e) {
            // 3. Prindem eroarea de duplicat (dacă există deja numărul de înmatriculare)
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
     */
    @PostMapping("/{id}/update")
    public String updateBus(@PathVariable Long id, @Valid @ModelAttribute("bus") Bus bus, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bus.setId(id);
            return "bus/edit_form";
        }

        bus.setId(id);
        busService.createBus(bus);
        return "redirect:/buses";
    }
}