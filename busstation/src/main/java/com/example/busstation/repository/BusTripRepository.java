package com.example.busstation.repository;

import com.example.busstation.model.BusTrip;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BusTripRepository {

    private final List<BusTrip> busTrips = new ArrayList<>();
    private long nextId = 1;

    public List<BusTrip> findAll() {
        return busTrips;
    }

    public Optional<BusTrip> findById(String id) {
        for (BusTrip busTrip : busTrips) {
            if (busTrip.getId().equals(id)) {
                return Optional.of(busTrip);
            }
        }
        return Optional.empty();
    }

    public BusTrip save(BusTrip busTrip) {
        if (busTrip.getId() == null || busTrip.getId().isEmpty()) {
            String newId = String.valueOf(nextId++);
            busTrip.setId(newId);
            busTrips.add(busTrip);
        } else {
            Optional<BusTrip> existingOpt = findById(busTrip.getId());
            if (existingOpt.isPresent()) {
                busTrips.remove(existingOpt.get());
            }
            busTrips.add(busTrip);
        }
        return busTrip;
    }

    public void delete(String id) {
        busTrips.removeIf(busTrip -> busTrip.getId().equals(id));
    }
}