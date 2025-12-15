package com.example.busstation.repository;

import com.example.busstation.model.BusTrip;
import com.example.busstation.model.enums.BusTripStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Long> {

    // Căutare flexibilă:
    // - Dacă 'status' e null, ignoră filtrul de status.
    // - Dacă 'busName' e null, ignoră filtrul de nume.
    @Query("SELECT t FROM BusTrip t WHERE " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:busName IS NULL OR LOWER(t.bus.name) LIKE LOWER(CONCAT('%', :busName, '%')))")
    List<BusTrip> searchBusTrips(
            @Param("status") BusTripStatus status,
            @Param("busName") String busName,
            Sort sort);
}