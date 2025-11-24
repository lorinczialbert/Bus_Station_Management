package com.example.busstation.service;

import com.example.busstation.model.Route;
import com.example.busstation.repository.IRepository; // MODIFICAT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final IRepository<Route,String> routeRepository; // MODIFICAT

    @Autowired
    public RouteService(IRepository<Route,String> routeRepository) { // MODIFICAT
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(String id) {
        return routeRepository.findById(id);
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public void deleteRoute(String id) {
        routeRepository.delete(id);
    }
}