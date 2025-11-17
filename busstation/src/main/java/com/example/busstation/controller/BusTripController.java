package com.example.busstation.controller;

import com.example.busstation.model.BusTrip;
import com.example.busstation.service.BusService;  // <-- *IMPORT HINZUFÜGEN*
import com.example.busstation.service.BusTripService;
import com.example.busstation.service.RouteService; // <-- *IMPORT HINZUFÜGEN*
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bustrips")
public class BusTripController extends AbstractBaseController {

    private final BusTripService busTripService;
    private final BusService busService;        // <-- *SERVICE HINZUFÜGEN*
    private final RouteService routeService;    // <-- *SERVICE HINZUFÜGEN*

    // Konstruktor anpassen
    public BusTripController(BusTripService busTripService, BusService busService, RouteService routeService) {
        this.busTripService = busTripService;
        this.busService = busService;           // <-- *SERVICE HINZUFÜGEN*
        this.routeService = routeService;       // <-- *SERVICE HINZUFÜGEN*
    }

    /**
     * Zeigt die LISTE aller BusTrips an.
     * Mapped auf: GET /bustrips
     */
    @GetMapping
    public String showBusTripList(Model model) {
        model.addAttribute("busTrips", busTripService.getAllBusTrips());
        return "bustrip/index";
    }

    // --- *NEUE METHODEN HINZUFÜGEN* ---

    /**
     * Zeigt das FORMULAR zum Erstellen eines neuen BusTrip an.
     * Mapped auf: GET /bustrips/new
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // Leeres Objekt für das Formular
        model.addAttribute("busTrip", new BusTrip());

        // Listen für die Dropdowns bereitstellen
        model.addAttribute("allRoutes", routeService.getAllRoutes());
        model.addAttribute("allBuses", busService.getAllBusse());

        // Sucht nach /resources/templates/bustrip/form.html
        return "bustrip/form";
    }

    /**
     * Verarbeitet das FORMULAR (erstellt den BusTrip).
     * Mapped auf: POST /bustrips
     */
    @PostMapping
    public String createBusTrip(@ModelAttribute BusTrip busTrip) {
        busTripService.createBusTrip(busTrip);
        // Leitet zurück zur Liste
        return "redirect:/bustrips";
    }

    // --- *ENDE NEUE METHODEN* ---


    /**
     * Löscht einen BusTrip.
     * Mapped auf: POST /bustrips/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteBusTrip(@PathVariable String id) {
        busTripService.deleteBusTrip(id);
        return "redirect:/bustrips";
    }
    /**
     * NOU: Afișează pagina de DETALII.
     * Mapat la: GET /bustrips/{id}/details
     */
    @GetMapping("/{id}/details")
    public String showBusTripDetails(@PathVariable String id, Model model) {
        model.addAttribute("busTrip", busTripService.getBusTripById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID BusTrip invalid:" + id)));
        // Puteți adăuga și busService/routeService pentru a afișa nume, nu doar ID-uri
        return "bustrip/details";
    }

    /**
     * NOU: Afișează FORMULARUL DE MODIFICARE (Editare).
     * Mapat la: GET /bustrips/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        BusTrip busTrip = busTripService.getBusTripById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID BusTrip invalid:" + id));
        model.addAttribute("busTrip", busTrip);

        // Listen für die Dropdowns bereitstellen
        model.addAttribute("allRoutes", routeService.getAllRoutes());
        model.addAttribute("allBuses", busService.getAllBusse());

        return "bustrip/edit_form";
    }

    /**
     * NOU: Procesează FORMULARUL DE MODIFICARE.
     * Mapat la: POST /bustrips/{id}/update
     */
    @PostMapping("/{id}/update")
    public String updateBusTrip(@PathVariable String id, @ModelAttribute BusTrip busTrip) {
        busTrip.setId(id);
        busTripService.createBusTrip(busTrip);
        return "redirect:/bustrips";
    }
}