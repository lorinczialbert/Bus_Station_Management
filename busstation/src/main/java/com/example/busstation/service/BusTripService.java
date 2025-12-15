package com.example.busstation.service;

import com.example.busstation.model.BusTrip;
import com.example.busstation.model.enums.BusTripStatus;
import com.example.busstation.repository.BusTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusTripService {

    private final BusTripRepository busTripRepository;

    @Autowired
    public BusTripService(BusTripRepository busTripRepository) {
        this.busTripRepository = busTripRepository;
    }

    // --- METODA 1: Compatibilitate (Overloading) ---
    // Aceasta previne erorile în alte părți ale aplicației care nu folosesc filtre
    public List<BusTrip> getAllBusTrips() {
        return getAllBusTrips(null, null, "id", "asc");
    }

    // --- METODA 2: Filtrare și Sortare ---
    public List<BusTrip> getAllBusTrips(BusTripStatus status, String busName, String sortBy, String sortDir) {

        // 1. Configurare direcție sortare
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }

        // 2. Configurare câmp sortare
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        // 3. Gestionare string gol (busName)
        String busNameFilter = (busName != null && !busName.isEmpty()) ? busName : null;

        // 4. Apel repository (status poate fi null direct)
        return busTripRepository.searchBusTrips(status, busNameFilter, sort);
    }

    public Optional<BusTrip> getBusTripById(Long id) {
        return busTripRepository.findById(id);
    }

    public BusTrip createBusTrip(BusTrip busTrip) {
        return busTripRepository.save(busTrip);
    }

    public void deleteBusTrip(Long id) {
        busTripRepository.deleteById(id);
    }
}