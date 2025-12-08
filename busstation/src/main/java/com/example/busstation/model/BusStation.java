package com.example.busstation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.CascadeType;

import java.util.List;

@Entity
@Table(name = "bus_stations")
public class BusStation extends BaseEntity {

    @NotBlank(message = "Numele stației este obligatoriu")
    private String name;

    @NotBlank(message = "Orașul este obligatoriu")
    private String city;

    // Aceasta este relația inversă (opțională, dar utilă).
    // Un Station poate fi originea multor Rute.
    // "mappedBy" se referă la câmpul 'origin' din clasa Route.
    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Route> routesStartingHere;

    public BusStation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Route> getRoutesStartingHere() {
        return routesStartingHere;
    }

    public void setRoutesStartingHere(List<Route> routesStartingHere) {
        this.routesStartingHere = routesStartingHere;
    }
}