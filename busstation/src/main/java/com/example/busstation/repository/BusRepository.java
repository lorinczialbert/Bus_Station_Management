package com.example.busstation.repository;

import com.example.busstation.model.Bus;
import com.example.busstation.model.enums.BusStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    // Metoda de căutare complexă
    // Caută după nume (parțial), status și capacitate minimă
    @Query("SELECT b FROM Bus b WHERE " +
            "(:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:status IS NULL OR b.status = :status) AND " +
            "(:minCapacity IS NULL OR b.capacity >= :minCapacity)")
    List<Bus> searchBuses(
            @Param("name") String name,
            @Param("status") BusStatus status,
            @Param("minCapacity") Integer minCapacity,
            Sort sort);
}