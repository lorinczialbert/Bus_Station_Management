package com.example.busstation.repository;

import com.example.busstation.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository implements ITicketRepository {

    private final List<Ticket> tickets = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<Ticket> findAll() {
        return tickets;
    }

    @Override
    public Optional<Ticket> findById(String id) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketID().equals(id)) {
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (ticket.getTicketID() == null || ticket.getTicketID().isEmpty()) {
            String newId = String.valueOf(nextId++);
            ticket.setTicketID(newId);
            tickets.add(ticket);
        } else {
            Optional<Ticket> existingOpt = findById(ticket.getTicketID());
            if (existingOpt.isPresent()) {
                tickets.remove(existingOpt.get());
            }
            tickets.add(ticket);
        }
        return ticket;
    }

    @Override
    public void delete(String id) {
        tickets.removeIf(ticket -> ticket.getTicketID().equals(id));
    }
}