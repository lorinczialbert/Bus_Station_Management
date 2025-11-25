package com.example.busstation.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Clasa de bază pentru toate entitățile JPA.
 * Folosim @MappedSuperclass pentru ca Hibernate să includă câmpul 'id' în tabelele copiilor.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL Auto-Increment
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}