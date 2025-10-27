package com.example.busstation.repository;

import com.example.busstation.model.BusStation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BusStationRepository {

    private final List<BusStation> busStations = new ArrayList<>();
    private long nextId = 1;

    public List<BusStation> findAll() {
        return busStations;
    }

    public Optional<BusStation> findById(String id) {
        for (BusStation busStation : busStations) {
            if (busStation.getId().equals(id)) {
                return Optional.of(busStation);
            }
        }
        return Optional.empty();
    }

    public BusStation save(BusStation busStation) {
        if (busStation.getId() == null || busStation.getId().isEmpty()) {
            String newId = String.valueOf(nextId++);
            busStation.setId(newId);
            busStations.add(busStation);
        } else {
            Optional<BusStation> existingOpt = findById(busStation.getId());
            if (existingOpt.isPresent()) {
                busStations.remove(existingOpt.get());
            }
            busStations.add(busStation);
        }
        return busStation;
    }

    public void delete(String id) {
        busStations.removeIf(busStation -> busStation.getId().equals(id));
    }
}