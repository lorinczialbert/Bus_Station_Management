package com.example.busstation.model;

import java.util.List;

// Clasa moștenește "Staff" (care acum are getId/setId)
public class TripManager extends Staff {
    private List<DutyAssignment> Assignments;
    private String EmployeeCode;
    // Am șters "EmployeeName" - folosește getStaffName() în schimb
    private String Salary;

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