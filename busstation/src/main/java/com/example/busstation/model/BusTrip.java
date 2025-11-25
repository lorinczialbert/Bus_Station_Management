package com.example.busstation.model;

import com.example.busstation.model.enums.BusTripStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "bus_trips")
public class BusTrip extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    @NotNull(message = "Ruta este obligatorie")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    @NotNull(message = "Autobuzul este obligatoriu")
    private Bus bus;

    // Păstrăm String pentru simplitate momentan, dar ideal ar fi LocalDateTime
    @NotNull(message = "Ora de plecare este obligatorie")
    private String startTime;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<DutyAssignment> assignments;

    @Enumerated(EnumType.STRING)
    private BusTripStatus status;

    public BusTrip() {
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<DutyAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<DutyAssignment> assignments) {
        this.assignments = assignments;
    }

    public BusTripStatus getStatus() {
        return status;
    }

    public void setStatus(BusTripStatus status) {
        this.status = status;
    }
}