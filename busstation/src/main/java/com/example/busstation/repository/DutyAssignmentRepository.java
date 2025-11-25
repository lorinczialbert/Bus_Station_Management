package com.example.busstation.repository;

import com.example.busstation.model.DutyAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyAssignmentRepository extends JpaRepository<DutyAssignment, Long> {
    // Aici poți defini metode custom dacă ai nevoie, ex:
    // List<Bus> findByStatus(BusStatus status);
    // Dar metodele de bază (save, findAll, findById, deleteById) sunt deja incluse!
}