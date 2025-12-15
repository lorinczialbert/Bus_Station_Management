package com.example.busstation.repository;

import com.example.busstation.model.DutyAssignment;
import com.example.busstation.model.enums.DriverRole;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyAssignmentRepository extends JpaRepository<DutyAssignment, Long> {

    // Căutare după Rol și Numele Angajatului (Staff Name)
    @Query("SELECT d FROM DutyAssignment d WHERE " +
            "(:role IS NULL OR d.role = :role) AND " +
            "(:staffName IS NULL OR LOWER(d.staff.staffName) LIKE LOWER(CONCAT('%', :staffName, '%')))")
    List<DutyAssignment> searchAssignments(
            @Param("role") DriverRole role,
            @Param("staffName") String staffName,
            Sort sort);
}