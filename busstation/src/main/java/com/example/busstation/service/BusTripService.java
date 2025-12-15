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

    // --- METODA DE COMPATIBILITATE (Fix-ul pentru eroare) ---
    // Aceasta este apelată de alte controllere care nu trimit filtre (ex: dropdown-uri)
    public List<BusTrip> getAllBusTrips() {
        return getAllBusTrips(null, null, "id", "asc");
    }

    // --- METODA PRINCIPALĂ CU FILTRE ȘI SORTARE ---
    public List<BusTrip> getAllBusTrips(BusTripStatus status, String busName, String sortBy, String sortDir) {

        // 1. Configurare Direcție Sortare
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }

        // 2. Configurare Câmp Sortare (implicit 'id')
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        // 3. Gestionare string gol pentru căutare
        String busNameFilter = (busName != null && !busName.isEmpty()) ? busName : null;

        // 4. Apelare Repository
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