package com.example.busstation.model;

import java.util.List;

public class BusStation implements BaseEntity {
    private String Id;
    private String Name;
    private String City;
    private List<BusTrip> Trips;

    public BusStation() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public List<BusTrip> getTrips() {
        return Trips;
    }

    public void setTrips(List<BusTrip> trips) {
        Trips = trips;
    }
}
