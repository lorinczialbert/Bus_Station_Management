package com.example.busstation.service;

import com.example.busstation.model.BusStation;
import com.example.busstation.repository.BusStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<BusStation> getAllBusStations() {
        return busStationRepository.findAll();
    }

    // ATENȚIE: ID-ul este acum Long
    public Optional<BusStation> getBusStationById(Long id) {
        return busStationRepository.findById(id);
    }

    public BusStation createBusStation(BusStation busStation) {
        return busStationRepository.save(busStation);
    }

    // ATENȚIE: ID-ul este acum Long și folosim deleteById
    public void deleteBusStation(Long id) {
        busStationRepository.deleteById(id);
    }
}