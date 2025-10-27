package com.example.busstation.repository;

import com.example.busstation.model.Bus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // WICHTIG!
public class BusRepository {

    // In-Memory-Speicher, NUR mit einer List
    private final List<Bus> busse = new ArrayList<>();
    private long nextId = 1; // Zähler für IDs

    /**
     * Findet alle Busse
     */
    public List<Bus> findAll() {
        // Einfach die ganze Liste zurückgeben
        return busse;
    }

    /**
     * Findet einen Bus anhand seiner ID
     * (Muss die Liste durchsuchen)
     */
    public Optional<Bus> findById(String id) {
        // Geht jeden Bus in der Liste durch...
        for (Bus bus : busse) {
            // ...wenn die ID passt...
            if (bus.getId().equals(id)) {
                // ...gib den Bus zurück.
                return Optional.of(bus);
            }
        }
        // Wenn nichts gefunden wurde, gib ein leeres Optional zurück.
        return Optional.empty();
    }

    /**
     * Speichert einen Bus (neu oder Update)
     */
    public Bus save(Bus bus) {
        if (bus.getId() == null || bus.getId().isEmpty()) {
            // 1. NEUER BUS: ID generieren
            String newId = String.valueOf(nextId++);
            bus.setId(newId);
            // 2. Zur Liste hinzufügen
            busse.add(bus);
        } else {
            // 3. UPDATE (Existierender Bus):
            // 3a. Finde den alten Bus
            Optional<Bus> existingBusOpt = findById(bus.getId());
            if (existingBusOpt.isPresent()) {
                // 3b. Entferne den alten Bus aus der Liste
                busse.remove(existingBusOpt.get());
            }
            // 3c. Füge den (aktualisierten) Bus wieder hinzu
            busse.add(bus);
        }
        return bus;
    }

    /**
     * Löscht einen Bus anhand seiner ID
     */
    public void delete(String id) {
        // Entfernt den Bus aus der Liste, falls die ID passt.
        busse.removeIf(bus -> bus.getId().equals(id));
    }
}