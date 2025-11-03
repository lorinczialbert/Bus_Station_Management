package com.example.busstation.controller;

import com.example.busstation.model.Bus;
import com.example.busstation.service.BusService;
import org.springframework.stereotype.Controller; // WICHTIG: Nicht @RestController
import org.springframework.ui.Model; // WICHTIG: Um Daten an HTML zu senden
import org.springframework.web.bind.annotation.*;

// 1. Dies ist jetzt ein @Controller, kein @RestController
@Controller
// 2. Die URL ist jetzt /buses (ohne /api), wie in P2 gefordert
@RequestMapping("/buses")
public class BusController extends AbstractBaseController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    /**
     * Zeigt die LISTE aller Busse an.
     * Mapped auf: GET /buses
     */
    @GetMapping
    public String showBusList(Model model) {
        // 1. Daten vom Service holen
        model.addAttribute("buses", busService.getAllBusse());
        // 2. Den Namen der HTML-Datei zurückgeben
        // (Spring sucht in resources/templates/bus/index.html)
        return "bus/index";
    }

    /**
     * Zeigt das FORMULAR zum Erstellen eines neuen Busses an.
     * Mapped auf: GET /buses/new
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // Erstellt ein leeres Bus-Objekt, an das das Formular binden kann
        model.addAttribute("bus", new Bus());
        // (Spring sucht in resources/templates/bus/form.html)
        return "bus/form";
    }

    /**
     * Verarbeitet das FORMULAR (erstellt den Bus).
     * Mapped auf: POST /buses
     */
    @PostMapping
    public String createBus(@ModelAttribute Bus bus) {
        // @ModelAttribute nimmt die Formulardaten und baut ein Bus-Objekt
        busService.createBus(bus);
        // Leitet den Benutzer zurück zur Liste (GET /buses)
        return "redirect:/buses";
    }

    /**
     * Löscht einen Bus.
     * Mapped auf: POST /buses/{id}/delete
     * (Wir verwenden POST, wie in P2 gefordert)
     */
    @PostMapping("/{id}/delete")
    public String deleteBus(@PathVariable String id) {
        busService.deleteBus(id);
        // Leitet den Benutzer zurück zur Liste (GET /buses)
        return "redirect:/buses";
    }
}