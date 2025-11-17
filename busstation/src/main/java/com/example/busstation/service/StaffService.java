package com.example.busstation.service;

import com.example.busstation.model.Staff;
import com.example.busstation.repository.IStaffRepository; // MODIFICAT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final IStaffRepository staffRepository; // MODIFICAT

    @Autowired
    public StaffService(IStaffRepository staffRepository) { // MODIFICAT
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(String id) {
        return staffRepository.findById(id);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public void deleteStaff(String id) {
        staffRepository.delete(id);
    }
}