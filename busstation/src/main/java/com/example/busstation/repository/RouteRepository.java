package com.example.busstation.repository;

import com.example.busstation.model.Route;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RouteRepository implements IRouteRepository {

    private final List<Route> routes = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<Route> findAll() {
        return routes;
    }

    @Override
    public Optional<Route> findById(String id) {
        for (Route route : routes) {
            if (route.getId().equals(id)) {
                return Optional.of(route);
            }
        }
        return Optional.empty();
    }

    @Override
    public Route save(Route route) {
        if (route.getId() == null || route.getId().isEmpty()) {
            String newId = String.valueOf(nextId++);
            route.setId(newId);
            routes.add(route);
        } else {
            Optional<Route> existingOpt = findById(route.getId());
            if (existingOpt.isPresent()) {
                routes.remove(existingOpt.get());
            }
            routes.add(route);
        }
        return route;
    }

    @Override
    public void delete(String id) {
        routes.removeIf(route -> route.getId().equals(id));
    }
}