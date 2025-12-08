package com.example.busstation.model;

import com.example.busstation.model.enums.BusStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "buses")
public class Bus extends BaseEntity {

    @NotBlank(message = "Numărul de înmatriculare este obligatoriu")
    @Column(name = "registration_number", nullable = false, unique = true)
    private String name; // Registration Number

    @Min(value = 10, message = "Capacitatea trebuie să fie de cel puțin 10 locuri")
    private int capacity;

    @Enumerated(EnumType.STRING)
    private BusStatus status;

    @NotNull(message = "Data fabricației este obligatorie")
    @PastOrPresent(message = "Data fabricației nu poate fi în viitor")
    private LocalDate factoryDate;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<BusTrip> trips;

    public Bus() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BusStatus getStatus() {
        return status;
    }

    public void setStatus(BusStatus status) {
        this.status = status;
    }

    public LocalDate getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(LocalDate factoryDate) {
        this.factoryDate = factoryDate;
    }
}