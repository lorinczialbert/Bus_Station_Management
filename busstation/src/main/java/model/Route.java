package model;

import java.util.List;

public class Route {
    private String Id;
    private BusTrip Origin;
    private BusTrip Destination;
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
    public BusTrip getOrigin() {
        return Origin;
    }
    public void setOrigin(BusTrip origin) {
        Origin = origin;
    }
    public BusTrip getDestination() {
        return Destination;
    }
    public void setDestination(BusTrip destination) {
        Destination = destination;
    }
    public double getDistance() {
        return Distance;
    }
    public void setDistance(double distance) {
        Distance = distance;
    }


}
