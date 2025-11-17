package com.example.busstation.model;

// 1. ADD THIS IMPORT
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

// 2. ADD THIS ANNOTATION
@JsonTypeName("driver")
public class Driver extends Staff {
    private List<DutyAssignment> assignedDutyList;
    private Integer yearsOfExperience;
    private String baseSalary; //atribut extra
    private String name;

    // ... rest of your Driver class ...

    public List<DutyAssignment> getAssignedDutyList() {
        return assignedDutyList;
    }

    public void setAssignedDutyList(List<DutyAssignment> assignedDutyList) {
        this.assignedDutyList = assignedDutyList;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(String baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Driver() {
    }
}