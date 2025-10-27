package com.example.busstation.repository;

import com.example.busstation.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // WICHTIG!
public class PassengerRepository {

    // In-Memory-Speicher, NUR mit einer List
    private final List<Passenger> passengers = new ArrayList<>();
    private long nextId = 1; // Zähler für IDs

    /**
     * Findet alle Passagiere
     */
    public List<Passenger> findAll() {
        return passengers;
    }

    /**
     * Findet einen Passagier anhand seiner ID
     */
    public Optional<Passenger> findById(String id) {
        // Geht jeden Passagier in der Liste durch...
        for (Passenger passenger : passengers) {
            // ...wenn die ID passt...
            if (passenger.getId().equals(id)) {
                // ...gib den Passagier zurück.
                return Optional.of(passenger);
            }
        }
        // Wenn nichts gefunden wurde, gib ein leeres Optional zurück.
        return Optional.empty();
    }

    /**
     * Speichert einen Passagier (neu oder Update)
     */
    public Passenger save(Passenger passenger) {
        if (passenger.getId() == null || passenger.getId().isEmpty()) {
            // 1. NEUER PASSAGIER: ID generieren
            String newId = String.valueOf(nextId++);
            passenger.setId(newId);
            // 2. Zur Liste hinzufügen
            passengers.add(passenger);
        } else {
            // 3. UPDATE:
            // 3a. Finde den alten Passagier
            Optional<Passenger> existingPassengerOpt = findById(passenger.getId());
            if (existingPassengerOpt.isPresent()) {
                // 3b. Entferne den alten Passagier
                passengers.remove(existingPassengerOpt.get());
            }
            // 3c. Füge den (aktualisierten) Passagier wieder hinzu
            passengers.add(passenger);
        }
        return passenger;
    }

    /**
     * Löscht einen Passagier anhand seiner ID
     */
    public void delete(String id) {
        // Entfernt den Passagier aus der Liste, falls die ID passt.
        passengers.removeIf(passenger -> passenger.getId().equals(id));
    }
}