package com.example.busstation.repository;

import com.example.busstation.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PassengerRepository implements IPassengerRepository {

    private final List<Passenger> passengers = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<Passenger> findAll() {
        return passengers;
    }

    @Override
    public Optional<Passenger> findById(String id) {
        for (Passenger passenger : passengers) {
            if (passenger.getId().equals(id)) {
                return Optional.of(passenger);
            }
        }
        return Optional.empty();
    }

    @Override
    public Passenger save(Passenger passenger) {
        if (passenger.getId() == null || passenger.getId().isEmpty()) {
            String newId = String.valueOf(nextId++);
            passenger.setId(newId);
            passengers.add(passenger);
        } else {
            Optional<Passenger> existingPassengerOpt = findById(passenger.getId());
            if (existingPassengerOpt.isPresent()) {
                passengers.remove(existingPassengerOpt.get());
            }
            passengers.add(passenger);
        }
        return passenger;
    }

    @Override
    public void delete(String id) {
        passengers.removeIf(passenger -> passenger.getId().equals(id));
    }
}