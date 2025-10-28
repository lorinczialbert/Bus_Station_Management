package com.example.busstation.service;

import com.example.busstation.model.Bus;
import com.example.busstation.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // WICHTIG: Sagt Spring, dass dies eine Service-Klasse ist
public class BusService {

    // Der Service braucht das Repository, um Daten zu speichern/holen.
    private final BusRepository busRepository;

    // "Dependency Injection": Spring gibt uns automatisch das Repository,
    // wenn der Service erstellt wird.
    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    // --- Hier ist die "Geschäftslogik" ---

    // Logik, um alle Busse zu holen
    public List<Bus> getAllBusse() {
        // Momentan rufen wir nur das Repo auf.
        // Spaeter koennte hier mehr Logik stehen (z.B. Busse filtern).
        return busRepository.findAll();
    }

    // Logik, um einen Bus nach ID zu holen
    public Optional<Bus> getBusById(String id) {
        return busRepository.findById(id);
    }

    // Logik, um einen neuen Bus zu erstellen
    public Bus createBus(Bus bus) {
        // Hier koennte Logik stehen, z.B. pruefen, ob die 'licensePlate' schon existiert.
        // Wir speichern ihn einfach.
        return busRepository.save(bus);
    }

    // Logik, um einen Bus zu loeschen
    public void deleteBus(String id) {
        busRepository.delete(id);
    }
}