package com.example.busstation.repository;

import com.example.busstation.model.Bus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 1. Implementează noua interfață
@Repository
public class BusRepository extends InFileRepository<Bus, String> {
    public BusRepository() {
        // Spunem clasei parinte:
        // 1. Folosește fișierul "data/bus.json"
        // 2. Când îl citesti, așteapta-te la un array de obiecte Bus (Bus[].class)
        super("data/bus.json", Bus[].class);
    }
}