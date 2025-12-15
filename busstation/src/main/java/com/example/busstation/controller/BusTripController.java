package com.example.busstation.controller;

import com.example.busstation.model.BusTrip;
import com.example.busstation.model.enums.BusTripStatus;
import com.example.busstation.service.BusService;
import com.example.busstation.service.BusTripService;
import com.example.busstation.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bustrips") // URL-ul în browser va fi: localhost:8080/bustrips
public class BusTripController {

    private final BusTripService busTripService;
    private final BusService busService;
    private final RouteService routeService;

    @Autowired
    public BusTripController(BusTripService busTripService, BusService busService, RouteService routeService) {
        this.busTripService = busTripService;
        this.busService = busService;
        this.routeService = routeService;
    }

    @GetMapping
    public String showBusTripList(
            Model model,
            @RequestParam(required = false) BusTripStatus searchStatus,
            @RequestParam(required = false) String searchBusName,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        model.addAttribute("busTrips", busTripService.getAllBusTrips(searchStatus, searchBusName, sortBy, sortDir));
        model.addAttribute("searchStatus", searchStatus);
        model.addAttribute("searchBusName", searchBusName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        // IMPORTANT: Aici indicăm folderul SINGULAR "bustrip"
        return "bustrip/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("busTrip", new BusTrip());
        model.addAttribute("buses", busService.getAllBusse());
        model.addAttribute("routes", routeService.getAllRoutes());
        return "bustrip/create"; // Folder singular
    }

    @PostMapping("/new")
    public String createBusTrip(@ModelAttribute BusTrip busTrip) {
        busTripService.createBusTrip(busTrip);
        return "redirect:/bustrips"; // Redirectează la URL-ul plural
    }

    @GetMapping("/{id}/details")
    public String getBusTripDetails(@PathVariable Long id, Model model) {
        model.addAttribute("busTrip", busTripService.getBusTripById(id).orElse(null));
        return "bustrip/details"; // Folder singular
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        BusTrip trip = busTripService.getBusTripById(id).orElse(null);
        if (trip != null) {
            model.addAttribute("busTrip", trip);
            model.addAttribute("buses", busService.getAllBusse());
            model.addAttribute("routes", routeService.getAllRoutes());
            return "bustrip/edit"; // Folder singular
        }
        return "redirect:/bustrips";
    }

    @PostMapping("/{id}/edit")
    public String updateBusTrip(@PathVariable Long id, @ModelAttribute BusTrip busTrip) {
        busTrip.setId(id);
        busTripService.createBusTrip(busTrip);
        return "redirect:/bustrips";
    }

    @PostMapping("/{id}/delete")
    public String deleteBusTrip(@PathVariable Long id) {
        busTripService.deleteBusTrip(id);
        return "redirect:/bustrips";
    }
}