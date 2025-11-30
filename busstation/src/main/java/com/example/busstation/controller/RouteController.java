package com.example.busstation.controller;

import com.example.busstation.model.Route;
import com.example.busstation.service.BusStationService;
import com.example.busstation.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final BusStationService busStationService;

    public RouteController(RouteService routeService, BusStationService busStationService) {
        this.routeService = routeService;
        this.busStationService = busStationService;
    }

    @GetMapping
    public String showRouteList(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes());
        return "route/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("route", new Route());
        model.addAttribute("allBusStations", busStationService.getAllBusStations());
        return "route/form";
    }

    @PostMapping
    public String createRoute(@Valid @ModelAttribute Route route, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allBusStations", busStationService.getAllBusStations());
            return "route/form";
        }
        routeService.createRoute(route);
        return "redirect:/routes";
    }

    @PostMapping("/{id}/delete")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "redirect:/routes";
    }

    @GetMapping("/{id}/details")
    public String showRouteDetails(@PathVariable Long id, Model model) {
        model.addAttribute("route", routeService.getRouteById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Route ID:" + id)));
        return "route/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Route route = routeService.getRouteById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Route ID:" + id));
        model.addAttribute("route", route);
        model.addAttribute("allBusStations", busStationService.getAllBusStations());
        return "route/edit_form";
    }

    @PostMapping("/{id}/update")
    public String updateRoute(@PathVariable Long id, @Valid @ModelAttribute Route route, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            route.setId(id);
            model.addAttribute("allBusStations", busStationService.getAllBusStations());
            return "route/edit_form";
        }
        route.setId(id);
        routeService.createRoute(route);
        return "redirect:/routes";
    }
}