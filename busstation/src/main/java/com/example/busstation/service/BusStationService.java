package com.example.busstation.service;

import com.example.busstation.model.BusStation;
import com.example.busstation.repository.BusStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusStationService {

    private final BusStationRepository busStationRepository;

    @Autowired
    public BusStationService(BusStationRepository busStationRepository) {
        this.busStationRepository = busStationRepository;
    }

    // FIX: Metoda simplă pentru compatibilitate
    public List<BusStation> getAllBusStations() {
        return getAllBusStations(null, null, "id", "asc");
    }

    // Metoda complexă
    public List<BusStation> getAllBusStations(String name, String city, String sortBy, String sortDir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        return busStationRepository.searchStations(name, city, sort);
    }

    public Optional<BusStation> getBusStationById(Long id) {
        return busStationRepository.findById(id);
    }

    public BusStation createBusStation(BusStation busStation) {
        return busStationRepository.save(busStation);
    }

    public void deleteBusStation(Long id) {
        busStationRepository.deleteById(id);
    }
}