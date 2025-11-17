package com.example.busstation.model;

/**
 * Interfață de bază pentru toate modelele (entitățile)
 * Garantează că fiecare model are un getter și setter pentru ID.
 */
public interface BaseEntity {
    String getId();
    void setId(String id);
}
