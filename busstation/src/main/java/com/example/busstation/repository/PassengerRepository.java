package com.example.busstation.repository;

import com.example.busstation.model.Passenger;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    // Căutare după nume (parțial) și vârstă minimă
    @Query("SELECT p FROM Passenger p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:minAge IS NULL OR p.age >= :minAge)")
    List<Passenger> searchPassengers(
            @Param("name") String name,
            @Param("minAge") Integer minAge,
            Sort sort);

    //void deleteByAge(Integer age);
}