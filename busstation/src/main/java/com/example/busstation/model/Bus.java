package com.example.busstation.model;

import java.time.LocalDate;

public class Bus {
    private String Id;
    private String RegistrationNumber;
    private int Capacity;
    private String Status;
    private LocalDate FactoryDate;

    public Bus() {

    }

    public String getId() {
        return Id;
    }

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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
    public LocalDate getFactoryDate() {
        return FactoryDate;
    }
    public void setFactoryDate(LocalDate factoryDate) {
        FactoryDate = factoryDate;
    }
}
