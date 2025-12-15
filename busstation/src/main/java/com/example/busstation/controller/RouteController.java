package com.example.busstation.controller;

import com.example.busstation.model.Route;
import com.example.busstation.service.BusStationService; // Dacă ai nevoie pentru dropdown-uri la Create/Edit
import com.example.busstation.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final BusStationService busStationService; // Presupunând că ai nevoie de stații pentru formularul de creare

    @Autowired
    public RouteController(RouteService routeService, BusStationService busStationService) {
        this.routeService = routeService;
        this.busStationService = busStationService;
    }

    @GetMapping
    public String showRouteList(
            Model model,
            @RequestParam(required = false) String searchOrigin,
            @RequestParam(required = false) String searchDest,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        // Apelăm service-ul cu toți parametrii
        model.addAttribute("routes", routeService.getAllRoutes(searchOrigin, searchDest, sortBy, sortDir));

        // Trimitem valorile înapoi în pagină pentru a păstra filtrele active și a genera link-urile de sortare
        model.addAttribute("searchOrigin", searchOrigin);
        model.addAttribute("searchDest", searchDest);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        // Calculăm sortarea inversă pentru link-urile din header-ul tabelului
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "route/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("route", new Route());
        model.addAttribute("busStations", busStationService.getAllBusStations());
        return "route/create";
    }

    @PostMapping("/new")
    public String createRoute(@ModelAttribute Route route) {
        routeService.createRoute(route);
        return "redirect:/routes";
    }

    @GetMapping("/{id}/details")
    public String getRouteDetails(@PathVariable Long id, Model model) {
        model.addAttribute("route", routeService.getRouteById(id).orElse(null));
        return "route/details";
    }

    // ... Metodele pentru Edit și Delete rămân la fel ...
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Route route = routeService.getRouteById(id).orElse(null);
        if (route != null) {
            model.addAttribute("route", route);
            model.addAttribute("busStations", busStationService.getAllBusStations());
            return "route/edit";
        }
        return "redirect:/routes";
    }

    @PostMapping("/{id}/edit")
    public String updateRoute(@PathVariable Long id, @ModelAttribute Route route) {
        route.setId(id);
        routeService.createRoute(route);
        return "redirect:/routes";
    }

    @PostMapping("/{id}/delete")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "redirect:/routes";
    }
}