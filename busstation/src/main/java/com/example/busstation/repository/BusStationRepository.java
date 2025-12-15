package com.example.busstation.repository;

import com.example.busstation.model.BusStation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BusStationRepository extends JpaRepository<BusStation, Long> {

    @Query("SELECT b FROM BusStation b WHERE " +
            "(:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:city IS NULL OR LOWER(b.city) LIKE LOWER(CONCAT('%', :city, '%')))")
    List<BusStation> searchStations(
            @Param("name") String name,
            @Param("city") String city,
            Sort sort);
}