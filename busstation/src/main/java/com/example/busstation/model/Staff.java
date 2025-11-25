package com.example.busstation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

// Nu mai avem nevoie de @JsonTypeInfo deoarece JPA se ocupă de tipuri
@Entity
@Table(name = "staff")
@Inheritance(strategy = InheritanceType.JOINED) // Strategie importantă pentru moștenire în DB
public abstract class Staff extends BaseEntity {

    @NotBlank(message = "Numele angajatului este obligatoriu")
    private String staffName;

    public Staff() {
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}