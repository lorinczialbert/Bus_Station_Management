package com.example.busstation.model;

public class Ticket implements BaseEntity {
    private String Id;
    private String tripID;
    private String passengerID;
    private String seatNumber;
    private double price;
    @Override
    public String getId() {
        return Id;
    }

    @Override
    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
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

    public Ticket() {
    }
}
