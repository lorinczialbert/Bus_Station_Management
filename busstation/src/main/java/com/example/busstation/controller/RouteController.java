package com.example.busstation.controller;

import com.example.busstation.model.Route;
import com.example.busstation.service.BusStationService;
import com.example.busstation.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/routes")
public class RouteController extends AbstractBaseController {

    private final RouteService routeService;
    private final BusStationService busStationService; // ZUSÄTZLICHER Service

    // Wir brauchen den BusStationService, um eine Liste der Stationen für das Formular zu laden
    public RouteController(RouteService routeService, BusStationService busStationService) {
        this.routeService = routeService;
        this.busStationService = busStationService;
    }

    /**
     * Zeigt die LISTE aller Routen an.
     */
    @GetMapping
    public String showRouteList(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes());
        return "route/index";
    }

    /**
     * Zeigt das FORMULAR zum Erstellen einer neuen Route an.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("route", new Route());
        // WICHTIG: Stellt die Liste aller Stationen für die Dropdowns bereit
        model.addAttribute("allBusStations", busStationService.getAllBusStations());
        return "route/form";
    }

    /**
     * Verarbeitet das FORMULAR (erstellt die Route).
     */
    @PostMapping
    public String createRoute(@ModelAttribute Route route) {
        // Thymeleaf/Spring binden die ausgewählten IDs automatisch an die Objekte Origin/Destination,
        // da wir im Formular th:field="*{origin}" und th:field="*{destination}" verwenden.
        // HINWEIS: Dies erfordert möglicherweise einen "Converter", aber oft funktioniert es
        // auch direkt, wenn das Repository `findById` implementiert hat.
        // Für P2 ist dieser einfache Ansatz ausreichend.

        // Da das Formular die IDs für Origin/Destination sendet, müssen wir die
        // vollen Objekte manuell setzen, bevor wir speichern.
        // (Wir holen die Objekte basierend auf den IDs, die Thymeleaf in die Felder 'origin' und 'destination' legt)

        // HINWEIS: Thymeleaf ist clever. Wenn 'origin' ein Objekt ist,
        // versucht es, die 'id' aus dem Formular zu nehmen und `setOrigin(busStationService.findById(id))` aufzurufen.
        // ABER: Es weiß nicht, welchen Service es verwenden soll.

        // DER EINFACHSTE WEG für P2 (ohne komplexe Converter):
        // Wir ändern, was wir vom Formular erwarten.

        // --- Dieser Code wird für ein einfaches Formular nicht benötigt, wenn th:field genutzt wird ---
        // Spring Boot sollte die IDs automatisch in die Objekte umwandeln, wenn
        // die Repositories vorhanden sind. Wir versuchen den einfachen Weg:
        routeService.createRoute(route);

        return "redirect:/routes";
    }

    /**
     * Löscht eine Route.
     */
    @PostMapping("/{id}/delete")
    public String deleteRoute(@PathVariable String id) {
        routeService.deleteRoute(id);
        return "redirect:/routes";
    }

    /**
     * NOU: Afișează pagina de DETALII.
     * Mapat la: GET /routes/{id}/details
     */
    @GetMapping("/{id}/details")
    public String showRouteDetails(@PathVariable String id, Model model) {
        model.addAttribute("route", routeService.getRouteById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Rută invalid:" + id)));
        return "route/details";
    }

    /**
     * NOU: Afișează FORMULARUL DE MODIFICARE (Editare).
     * Mapat la: GET /routes/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Route route = routeService.getRouteById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Rută invalid:" + id));
        model.addAttribute("route", route);
        // WICHTIG: Stellt die Liste aller Stationen für die Dropdowns bereit
        model.addAttribute("allBusStations", busStationService.getAllBusStations());
        return "route/edit_form";
    }

    /**
     * NOU: Procesează FORMULARUL DE MODIFICARE.
     * Mapat la: POST /routes/{id}/update
     */
    @PostMapping("/{id}/update")
    public String updateRoute(@PathVariable String id, @ModelAttribute Route route) {
        route.setId(id);
        routeService.createRoute(route);
        return "redirect:/routes";
    }
}