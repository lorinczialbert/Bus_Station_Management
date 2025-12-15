package com.example.busstation.controller;

import com.example.busstation.model.Ticket;
import com.example.busstation.service.BusTripService;
import com.example.busstation.service.PassengerService;
import com.example.busstation.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class  TicketController { // No extends AbstractBaseController

    private final TicketService ticketService;
    private final BusTripService busTripService;
    private final PassengerService passengerService;

    public TicketController(TicketService ticketService, BusTripService busTripService, PassengerService passengerService) {
        this.ticketService = ticketService;
        this.busTripService = busTripService;
        this.passengerService = passengerService;
    }

    @GetMapping
    public String showTicketList(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "ticket/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        // Load dependencies for dropdowns
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allPassengers", passengerService.getAllPassengers());
        return "ticket/form";
    }

    @PostMapping
    public String createTicket(@Valid @ModelAttribute Ticket ticket, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Reload lists for dropdowns if validation fails
            model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
            model.addAttribute("allPassengers", passengerService.getAllPassengers());
            return "ticket/form";
        }
        ticketService.createTicket(ticket);
        return "redirect:/tickets";
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("/{id}/details")
    public String showTicketDetails(@PathVariable Long id, Model model) {
        model.addAttribute("ticket", ticketService.getTicketById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Ticket ID:" + id)));
        return "ticket/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Ticket ID:" + id));
        model.addAttribute("ticket", ticket);

        // Load dependencies for dropdowns
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allPassengers", passengerService.getAllPassengers());
        return "ticket/edit_form";
    }

    @PostMapping("/{id}/update")
    public String updateTicket(@PathVariable Long id, @Valid @ModelAttribute Ticket ticket, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            ticket.setId(id);
            // Reload lists for dropdowns
            model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
            model.addAttribute("allPassengers", passengerService.getAllPassengers());
            return "ticket/edit_form";
        }
        ticket.setId(id);
        ticketService.createTicket(ticket);
        return "redirect:/tickets";
    }
}