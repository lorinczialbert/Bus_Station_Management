package com.example.busstation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver extends Staff {

    @OneToMany(mappedBy = "staff")
    private List<DutyAssignment> assignedDutyList;

    @Min(value = 0, message = "Anii de experiență nu pot fi negativi")
    private Integer yearsOfExperience;

    private String baseSalary;

    // Câmp redundant 'name' a fost șters, folosim 'staffName' din părinte

    public Driver() {
    }

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
}