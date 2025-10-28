package com.example.busstation.model;

import java.util.List;

public class Route {
    private String Id;

    // KORREKTUR: Dies sollte BusStation sein, um dem UML-Diagramm zu entsprechen.
    private BusStation Origin;
    private BusStation Destination;

    private double Distance;
    private List<BusTrip> Trips;

    public Route() {
    }

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }

    // Getter/Setter für BusStation
    public BusStation getOrigin() {
        return Origin;
    }
    public void setOrigin(BusStation origin) {
        Origin = origin;
    }
    public BusStation getDestination() {
        return Destination;
    }
    public void setDestination(BusStation destination) {
        Destination = destination;
    }

    public double getDistance() {
        return Distance;
    }
    public void setDistance(double distance) {
        Distance = distance;
    }

    public List<BusTrip> getTrips() {
        return Trips;
    }

    public void setTrips(List<BusTrip> trips) {
        Trips = trips;
    }
}
