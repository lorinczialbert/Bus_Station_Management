package com.example.busstation.service;

import com.example.busstation.model.Ticket;
import com.example.busstation.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // --- FIX: Compatibilitate ---
    public List<Ticket> getAllTickets() {
        return getAllTickets(null, null, "id", "asc");
    }

    // --- Metoda Complexă ---
    public List<Ticket> getAllTickets(String pName, Double minPrice, String sortBy, String sortDir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        return ticketRepository.searchTickets(pName, minPrice, sort);
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}