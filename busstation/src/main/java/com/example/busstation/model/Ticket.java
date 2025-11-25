package com.example.busstation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    @NotNull(message = "Călătoria este obligatorie")
    private BusTrip trip;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    @NotNull(message = "Pasagerul este obligatoriu")
    private Passenger passenger;

    @NotBlank(message = "Numărul locului este obligatoriu")
    private String seatNumber;

    @Positive(message = "Prețul trebuie să fie pozitiv")
    private double price;

    public Ticket() {
    }

    public BusTrip getTrip() {
        return trip;
    }

    public void setTrip(BusTrip trip) {
        this.trip = trip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}