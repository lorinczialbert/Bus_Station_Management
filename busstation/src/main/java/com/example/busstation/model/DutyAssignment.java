package com.example.busstation.model;
import com.example.busstation.model.enums.DriverRole;


public class DutyAssignment implements BaseEntity {
    private String Id;
    private String TripId;
    private String StaffId;
    private DriverRole Role;

    public DutyAssignment() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTripId() {
        return TripId;
    }

    public void setTripId(String tripId) {
        TripId = tripId;
    }

    public String getStaffId() {
        return StaffId;
    }

    public void setStaffId(String staffId) {
        StaffId = staffId;
    }

    public DriverRole getRole() {
        return Role;
    }

    // MODIFICAT: Setter-ul
    public void setRole(DriverRole role) {
        Role = role;
    }
}
