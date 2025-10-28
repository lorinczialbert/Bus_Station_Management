package com.example.busstation.service;

import com.example.busstation.model.BusTrip;
import com.example.busstation.repository.BusTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<BusTrip> getAllBusTrips() {
        return busTripRepository.findAll();
    }

    public Optional<BusTrip> getBusTripById(String id) {
        return busTripRepository.findById(id);
    }

    public BusTrip createBusTrip(BusTrip busTrip) {
        return busTripRepository.save(busTrip);
    }

    public void deleteBusTrip(String id) {
        busTripRepository.delete(id);
    }
}