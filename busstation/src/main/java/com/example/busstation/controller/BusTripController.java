package com.example.busstation.controller;

import com.example.busstation.model.BusTrip;
import com.example.busstation.service.BusService;
import com.example.busstation.service.BusTripService;
import com.example.busstation.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bustrips")
public class BusTripController {

    private final BusTripService busTripService;
    private final BusService busService;
    private final RouteService routeService;

    public BusTripController(BusTripService busTripService, BusService busService, RouteService routeService) {
        this.busTripService = busTripService;
        this.busService = busService;
        this.routeService = routeService;
    }

    @GetMapping
    public String showBusTripList(Model model) {
        model.addAttribute("busTrips", busTripService.getAllBusTrips());
        return "bustrip/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("busTrip", new BusTrip());
        // Load data for dropdowns
        model.addAttribute("allRoutes", routeService.getAllRoutes());
        model.addAttribute("allBuses", busService.getAllBusse());
        return "bustrip/form";
    }

    @PostMapping
    public String createBusTrip(@Valid @ModelAttribute BusTrip busTrip, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Reload dropdowns if validation fails
            model.addAttribute("allRoutes", routeService.getAllRoutes());
            model.addAttribute("allBuses", busService.getAllBusse());
            return "bustrip/form";
        }
        busTripService.createBusTrip(busTrip);
        return "redirect:/bustrips";
    }

    @PostMapping("/{id}/delete")
    public String deleteBusTrip(@PathVariable Long id) {
        busTripService.deleteBusTrip(id);
        return "redirect:/bustrips";
    }

    @GetMapping("/{id}/details")
    public String showBusTripDetails(@PathVariable Long id, Model model) {
        model.addAttribute("busTrip", busTripService.getBusTripById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid BusTrip ID:" + id)));
        return "bustrip/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        BusTrip busTrip = busTripService.getBusTripById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid BusTrip ID:" + id));
        model.addAttribute("busTrip", busTrip);

        // Load data for dropdowns
        model.addAttribute("allRoutes", routeService.getAllRoutes());
        model.addAttribute("allBuses", busService.getAllBusse());
        return "bustrip/edit_form";
    }

    @PostMapping("/{id}/update")
    public String updateBusTrip(@PathVariable Long id, @Valid @ModelAttribute BusTrip busTrip, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            busTrip.setId(id);
            // Reload dropdowns if validation fails
            model.addAttribute("allRoutes", routeService.getAllRoutes());
            model.addAttribute("allBuses", busService.getAllBusse());
            return "bustrip/edit_form";
        }
        busTrip.setId(id);
        busTripService.createBusTrip(busTrip);
        return "redirect:/bustrips";
    }
}