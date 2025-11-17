package com.example.busstation.model;

// 1. ADD THIS IMPORT
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

// 2. ADD THIS ANNOTATION
@JsonTypeName("manager")
// Clasa moștenește "Staff" (care acum are getId/setId)
public class TripManager extends Staff {
    private List<DutyAssignment> Assignments;
    private String EmployeeCode;
    // Am șters "EmployeeName" - folosește getStaffName() în schimb
    private String Salary;

    // ... rest of your TripManager class ...

    public TripManager() {
    }

    public List<DutyAssignment> getAssignments() {
        return Assignments;
    }

    public void setAssignments(List<DutyAssignment> assignments) {
        Assignments = assignments;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public String getSalary() {
        return Salary;
    }
    public void setSalary(String salary) {
        Salary = salary;
    }
}