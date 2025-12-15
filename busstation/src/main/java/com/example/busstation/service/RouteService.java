package com.example.busstation.service;

import com.example.busstation.model.Route;
import com.example.busstation.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    // --- METODA 1: Compatibilitate (Overloading) ---
    // Aceasta este apelată când nu se specifică filtre (ex: din dropdown-uri sau alte controllere)
    public List<Route> getAllRoutes() {
        return getAllRoutes(null, null, "id", "asc");
    }

    // --- METODA 2: Filtrare și Sortare (Logică principală) ---
    public List<Route> getAllRoutes(String origin, String dest, String sortBy, String sortDir) {

        // 1. Configurare direcție sortare
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDir)) {
            direction = Sort.Direction.DESC;
        }

        // 2. Configurare câmp sortare (default 'id')
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }
        Sort sort = Sort.by(direction, sortBy);

        // 3. Gestionare string-uri goale pentru căutare (transformare în null)
        String originFilter = (origin != null && !origin.isEmpty()) ? origin : null;
        String destFilter = (dest != null && !dest.isEmpty()) ? dest : null;

        return routeRepository.searchRoutes(originFilter, destFilter, sort);
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}