package com.example.busstation.service;

import com.example.busstation.model.Bus;
import com.example.busstation.repository.BusRepository; // Importă repository-ul corect
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    // Folosim direct interfața BusRepository, nu IRepository
    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getAllBusse() {
        return busRepository.findAll();
    }

    // ATENȚIE: Schimbare din String în Long
    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    // ATENȚIE: Schimbare din String în Long
    public void deleteBus(Long id) {
        busRepository.deleteById(id); // JpaRepository folosește deleteById
    }
}