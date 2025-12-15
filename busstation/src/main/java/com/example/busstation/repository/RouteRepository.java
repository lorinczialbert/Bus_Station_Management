package com.example.busstation.repository;

import com.example.busstation.model.Route;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r WHERE " +
            "(:origin IS NULL OR LOWER(r.origin.name) LIKE LOWER(CONCAT('%', :origin, '%'))) AND " +
            "(:dest IS NULL OR LOWER(r.destination.name) LIKE LOWER(CONCAT('%', :dest, '%')))")
    List<Route> searchRoutes(
            @Param("origin") String origin,
            @Param("dest") String dest,
            Sort sort);
}