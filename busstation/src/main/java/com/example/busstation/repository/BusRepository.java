package com.example.busstation.repository;

import com.example.busstation.model.Bus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 1. Implementează noua interfață
@Repository
public class BusRepository implements IBusRepository {

    private final List<Bus> busse = new ArrayList<>();
    private long nextId = 1;

    // 2. Adăugăm @Override
    @Override
    public List<Bus> findAll() {
        return busse;
    }

    @Override
    public Optional<Bus> findById(String id) {
        for (Bus bus : busse) {
            if (bus.getId().equals(id)) {
                return Optional.of(bus);
            }
        }
        return Optional.empty();
    }

    @Override
    public Bus save(Bus bus) {
        if (bus.getId() == null || bus.getId().isEmpty()) {
            String newId = String.valueOf(nextId++);
            bus.setId(newId);
            busse.add(bus);
        } else {
            Optional<Bus> existingBusOpt = findById(bus.getId());
            if (existingBusOpt.isPresent()) {
                busse.remove(existingBusOpt.get());
            }
            busse.add(bus);
        }
        return bus;
    }

    @Override
    public void delete(String id) {
        busse.removeIf(bus -> bus.getId().equals(id));
    }
}