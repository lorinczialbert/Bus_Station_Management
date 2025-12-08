package com.example.busstation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.CascadeType;

import java.util.List;

@Entity
@Table(name = "trip_managers")
public class TripManager extends Staff {

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DutyAssignment> assignments;

    @NotBlank(message = "Codul de angajat este obligatoriu")
    private String employeeCode;

    private String salary;

    public TripManager() {
    }

    public List<DutyAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<DutyAssignment> assignments) {
        this.assignments = assignments;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}