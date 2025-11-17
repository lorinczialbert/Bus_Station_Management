package com.example.busstation.service;

import com.example.busstation.model.Bus;
// 1. MODIFICARE: Importați Interfața
import com.example.busstation.repository.IBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    // 2. MODIFICARE: Tipul variabilei este acum Interfața
    private final IBusRepository busRepository;

    @Autowired
    // 3. MODIFICARE: Tipul parametrului din constructor este Interfața
    public BusService(IBusRepository busRepository) {
        this.busRepository = busRepository;
    }

    // --- Restul codului rămâne IDENTIC ---

    public List<Bus> getAllBusse() {
        return busRepository.findAll();
    }

    public Optional<Bus> getBusById(String id) {
        return busRepository.findById(id);
    }

    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteBus(String id) {
        busRepository.delete(id);
    }
}