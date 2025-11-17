package com.example.busstation.repository;

import com.example.busstation.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository extends InFileRepository<Ticket, String> implements ITicketRepository {

    public TicketRepository() {
        super("data/tickets.json", Ticket[].class);
    }
}