package com.example.busstation.service;

import com.example.busstation.model.Staff;
import com.example.busstation.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    // --- METODA 1: Compatibilitate (Overloading) ---
    // Previne erorile dacă metoda e apelată fără parametri în alte părți
    public List<Staff> getAllStaff() {
        return getAllStaff(null, "id", "asc");
    }

    // --- METODA 2: Filtrare și Sortare ---
    public List<Staff> getAllStaff(String searchName, String sortBy, String sortDir) {

        // 1. Configurare direcție sortare
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }

        // 2. Configurare câmp sortare
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        // 3. Gestionare string gol (searchName)
        String nameFilter = (searchName != null && !searchName.isEmpty()) ? searchName : null;

        return staffRepository.searchStaff(nameFilter, sort);
    }

    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }
}