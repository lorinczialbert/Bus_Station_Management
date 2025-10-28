package com.example.busstation.service;

import com.example.busstation.model.Route;
import com.example.busstation.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // WICHTIG!
public class RouteService {

    // Der Service braucht das RouteRepository
    private final RouteRepository routeRepository;

    // Dependency Injection
    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    // --- Geschäftslogik für Routen ---

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(String id) {
        return routeRepository.findById(id);
    }

    public Route createRoute(Route route) {
        // Logik vor dem Speichern (z.B. pruefen ob Origin == Destination)
        return routeRepository.save(route);
    }

    public void deleteRoute(String id) {
        routeRepository.delete(id);
    }
}