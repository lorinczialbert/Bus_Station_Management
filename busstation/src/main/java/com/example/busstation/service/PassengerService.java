package com.example.busstation.service;

import com.example.busstation.model.Passenger;
import com.example.busstation.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    // --- FIX: Metoda simplă pentru compatibilitate (dropdown-uri etc.) ---
    public List<Passenger> getAllPassengers() {
        // Apelează metoda complexă cu valori null (fără filtre, sortare implicită)
        return getAllPassengers(null, null, "id", "asc");
    }

    // --- Metoda complexă pentru Filtrare și Sortare ---
    public List<Passenger> getAllPassengers(String searchName, Integer minAge, String sortBy, String sortDir) {

        // 1. Configurare direcție sortare
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }

        // 2. Configurare câmp sortare (default 'id')
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        // 3. Apelare repository
        return passengerRepository.searchPassengers(searchName, minAge, sort);
    }

    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}