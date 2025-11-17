package com.example.busstation.repository;

import com.example.busstation.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository extends InFileRepository<Staff, String> implements IStaffRepository {

    public StaffRepository() {
        super("data/staff.json", Staff[].class);
    }
}