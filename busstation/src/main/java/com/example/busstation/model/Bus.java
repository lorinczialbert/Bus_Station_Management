package com.example.busstation.model;
import com.example.busstation.model.enums.BusStatus;

import java.time.LocalDate;

public class Bus implements BaseEntity {
    private String Id;
    private String RegistrationNumber;
    private int Capacity;
    private BusStatus Status;
    private LocalDate FactoryDate;

    public Bus() {

    }
    @Override
    public String getId() {
        return Id;
    }
    @Override
    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return RegistrationNumber;
    }

    public void setName(String name) {
        RegistrationNumber = name;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public BusStatus getStatus() {
        return Status;
    }

    // MODIFICAT: Setter-ul
    public void setStatus(BusStatus status) {
        Status = status;
    }
    public LocalDate getFactoryDate() {
        return FactoryDate;
    }
    public void setFactoryDate(LocalDate factoryDate) {
        FactoryDate = factoryDate;
    }
}
