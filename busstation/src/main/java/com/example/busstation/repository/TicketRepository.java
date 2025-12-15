package com.example.busstation.repository;

import com.example.busstation.model.Ticket;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Căutare după Nume Pasager și Preț Minim
    @Query("SELECT t FROM Ticket t WHERE " +
            "(:pName IS NULL OR LOWER(t.passenger.name) LIKE LOWER(CONCAT('%', :pName, '%'))) AND " +
            "(:minPrice IS NULL OR t.price >= :minPrice)")
    List<Ticket> searchTickets(
            @Param("pName") String pName,
            @Param("minPrice") Double minPrice,
            Sort sort);
}