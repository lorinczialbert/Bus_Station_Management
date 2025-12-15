package com.example.busstation.repository;

import com.example.busstation.model.Staff;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    // Căutare case-insensitive după numele angajatului
    @Query("SELECT s FROM Staff s WHERE " +
            "(:name IS NULL OR LOWER(s.staffName) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Staff> searchStaff(@Param("name") String name, Sort sort);
}