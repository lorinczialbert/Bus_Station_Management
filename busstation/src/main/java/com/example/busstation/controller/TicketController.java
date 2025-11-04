package com.example.busstation.controller;

import com.example.busstation.model.Ticket;
import com.example.busstation.service.BusTripService; // <-- *IMPORT HINZUFÜGEN*
import com.example.busstation.service.PassengerService; // <-- *IMPORT HINZUFÜGEN*
import com.example.busstation.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController extends AbstractBaseController {

    private final TicketService ticketService;
    private final BusTripService busTripService;     // <-- *SERVICE HINZUFÜGEN*
    private final PassengerService passengerService; // <-- *SERVICE HINZUFÜGEN*

    // Konstruktor anpassen
    public TicketController(TicketService ticketService, BusTripService busTripService, PassengerService passengerService) {
        this.ticketService = ticketService;
        this.busTripService = busTripService;       // <-- *SERVICE HINZUFÜGEN*
        this.passengerService = passengerService;   // <-- *SERVICE HINZUFÜGEN*
    }

    /**
     * Zeigt die LISTE aller Tickets an.
     * Mapped auf: GET /tickets
     */
    @GetMapping
    public String showTicketList(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "ticket/index";
    }

    // --- *NEUE METHODEN HINZUFÜGEN* ---

    /**
     * Zeigt das FORMULAR zum Erstellen eines neuen Tickets an.
     * Mapped auf: GET /tickets/new
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());

        // Listen für die Dropdowns
        model.addAttribute("allBusTrips", busTripService.getAllBusTrips());
        model.addAttribute("allPassengers", passengerService.getAllPassengers());

        return "ticket/form";
    }

    /**
     * Verarbeitet das FORMULAR (erstellt das Ticket).
     * Mapped auf: POST /tickets
     */
    @PostMapping
    public String createTicket(@ModelAttribute Ticket ticket) {
        ticketService.createTicket(ticket);
        return "redirect:/tickets";
    }

    // --- *ENDE NEUE METHODEN* ---

    /**
     * Löscht ein Ticket.
     * Mapped auf: POST /tickets/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }
}