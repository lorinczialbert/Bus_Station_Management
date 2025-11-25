package com.example.busstation.repository;

import com.example.busstation.model.BusTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Long> {
    // Aici poți defini metode custom dacă ai nevoie, ex:
    // List<Bus> findByStatus(BusStatus status);
    // Dar metodele de bază (save, findAll, findById, deleteById) sunt deja incluse!
}