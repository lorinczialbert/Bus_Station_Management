package com.example.busstation.controller;

import com.example.busstation.model.Ticket;
import com.example.busstation.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // WICHTIG: Geändert von @RestController
@RequestMapping("/tickets") // Pfad geändert (ohne /api)
public class TicketController extends AbstractBaseController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Zeigt die LISTE aller Tickets an.
     * Mapped auf: GET /tickets
     */
    @GetMapping
    public String showTicketList(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        // Sucht nach /resources/templates/ticket/index.html
        return "ticket/index";
    }

    /**
     * HINWEIS: GET /new und POST / werden weggelassen.
     * Das Formular ist komplex, da es Dropdowns für 'BusTrip' und 'Passenger' benötigt.
     */

    /**
     * Löscht ein Ticket.
     * Mapped auf: POST /tickets/{id}/delete
     */
    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
        // Leitet zurück zur Liste (GET /tickets)
        return "redirect:/tickets";
    }
}
