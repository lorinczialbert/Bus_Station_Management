package com.example.busstation.service;

import com.example.busstation.model.BusStation;
import com.example.busstation.repository.IRepository; // MODIFICAT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusStationService {

    private final IRepository<BusStation,String> busStationRepository; // MODIFICAT

    @Autowired
    public BusStationService(IRepository<BusStation,String> busStationRepository) { // MODIFICAT
        this.busStationRepository = busStationRepository;
    }

    public List<BusStation> getAllBusStations() {
        return busStationRepository.findAll();
    }

    public Optional<BusStation> getBusStationById(String id) {
        return busStationRepository.findById(id);
    }

    public BusStation createBusStation(BusStation busStation) {
        return busStationRepository.save(busStation);
    }

    public void deleteBusStation(String id) {
        busStationRepository.delete(id);
    }
}