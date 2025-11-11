package com.example.busstation.model;
import com.example.busstation.model.enums.BusTripStatus;

import java.util.List;

public class BusTrip {
    private String Id;
    private String RouteId;
    private String BusId;
    private String StartTime;
    private List<Ticket> Tickets;
    private List<DutyAssignment> Assignments;
    private BusTripStatus Status;

    public BusTrip() {
    }

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getRouteId() {
        return RouteId;
    }
    public void setRouteId(String routeId) {
        RouteId = routeId;
    }
    public String getBusId() {
        return BusId;
    }
    public void setBusId(String busId) {
        BusId = busId;
    }
    public String getStartTime() {
        return StartTime;
    }
    public void setStartTime(String startTime) {
        StartTime = startTime;
    }
    public List<Ticket> getTickets() {
        return Tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        Tickets = tickets;
    }
    public List<DutyAssignment> getAssignments() {
        return Assignments;
    }
    public void setAssignments(List<DutyAssignment> assignments) {
        Assignments = assignments;
    }
    public BusTripStatus getStatus() {
        return Status;
    }

    // MODIFICAT: Setter-ul
    public void setStatus(BusTripStatus status) {
        Status = status;
    }


}
