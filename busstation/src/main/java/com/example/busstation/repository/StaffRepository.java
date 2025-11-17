package com.example.busstation.repository;

import com.example.busstation.model.Staff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StaffRepository implements IStaffRepository {

    private final List<Staff> staffMembers = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<Staff> findAll() {
        return staffMembers;
    }

    @Override
    public Optional<Staff> findById(String id) {
        for (Staff staff : staffMembers) {
            if (staff.getStaffId().equals(id)) {
                return Optional.of(staff);
            }
        }
        return Optional.empty();
    }

    @Override
    public Staff save(Staff staff) {
        if (staff.getStaffId() == null || staff.getStaffId().isEmpty()) {
            String newId = String.valueOf(nextId++);
            staff.setStaffId(newId);
            staffMembers.add(staff);
        } else {
            Optional<Staff> existingOpt = findById(staff.getStaffId());
            if (existingOpt.isPresent()) {
                staffMembers.remove(existingOpt.get());
            }
            staffMembers.add(staff);
        }
        return staff;
    }

    @Override
    public void delete(String id) {
        staffMembers.removeIf(staff -> staff.getStaffId().equals(id));
    }
}