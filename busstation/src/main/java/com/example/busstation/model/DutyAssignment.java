package com.example.busstation.model;

import com.example.busstation.model.enums.DriverRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "duty_assignments")
public class DutyAssignment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    @NotNull(message = "Călătoria este obligatorie")
    private BusTrip trip;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    @NotNull(message = "Angajatul este obligatoriu")
    private Staff staff;

    @Enumerated(EnumType.STRING)
    private DriverRole role;

    public DutyAssignment() {
    }

    public BusTrip getTrip() {
        return trip;
    }

    public void setTrip(BusTrip trip) {
        this.trip = trip;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public DriverRole getRole() {
        return role;
    }

    public void setRole(DriverRole role) {
        this.role = role;
    }
}