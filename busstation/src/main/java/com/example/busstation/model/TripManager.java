package com.example.busstation.model;

import java.util.List;

public class TripManager extends Staff {
    private List<DutyAssignment> Assignments;
    private String EmployeeCode;
    private String EmployeeName;
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
    public String getEmployeeName() {
        return EmployeeName;
    }
    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }
    public String getSalary() {
        return Salary;
    }
    public void setSalary(String salary) {
        Salary = salary;
    }
}

