package com.example.busstation.service;

import com.example.busstation.model.Bus;
import com.example.busstation.model.enums.BusStatus;
import com.example.busstation.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    // --- FIX: Adăugăm această metodă pentru compatibilitate (Overloading) ---
    // Aceasta va fi apelată de BusTripController unde nu avem nevoie de filtre
    public List<Bus> getAllBusse() {
        return getAllBusse(null, null, null, "id", "asc");
    }

    // Metoda complexă pentru filtrare și sortare (folosită de BusController)
    public List<Bus> getAllBusse(String searchName, BusStatus searchStatus, Integer minCapacity, String sortBy, String sortDir) {

        // 1. Configurare Sortare
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }
        // Dacă sortBy e null sau gol, sortăm implicit după ID
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        // 2. Apelare Repository cu filtre
        // Dacă string-ul de căutare e gol, îl trimitem ca null
        String nameFilter = (searchName != null && !searchName.isEmpty()) ? searchName : null;

        return busRepository.searchBuses(nameFilter, searchStatus, minCapacity, sort);
    }

    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
}