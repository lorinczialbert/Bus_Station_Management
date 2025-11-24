package com.example.busstation.service;

import com.example.busstation.model.Passenger;
import com.example.busstation.repository.IRepository; // MODIFICAT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private final IRepository<Passenger,String> passengerRepository; // MODIFICAT

    @Autowired
    public PassengerService(IRepository<Passenger,String> passengerRepository) { // MODIFICAT
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> getPassengerById(String id) {
        return passengerRepository.findById(id);
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(String id) {
        passengerRepository.delete(id);
    }
}