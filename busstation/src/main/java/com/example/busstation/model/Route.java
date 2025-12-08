package com.example.busstation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "routes")
public class Route extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    @NotNull(message = "Stația de plecare este obligatorie")
    private BusStation origin;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    @NotNull(message = "Stația de sosire este obligatorie")
    private BusStation destination;

    @Positive(message = "Distanța trebuie să fie pozitivă")
    private double distance;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusTrip> trips;

    public Route() {
    }

    public BusStation getOrigin() {
        return origin;
    }

    public void setOrigin(BusStation origin) {
        this.origin = origin;
    }

    public BusStation getDestination() {
        return destination;
    }

    public void setDestination(BusStation destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<BusTrip> getTrips() {
        return trips;
    }

    public void setTrips(List<BusTrip> trips) {
        this.trips = trips;
    }
}