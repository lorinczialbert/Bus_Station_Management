package com.example.busstation.repository;

import com.example.busstation.model.Route;
import org.springframework.stereotype.Repository;

@Repository
public class RouteRepository extends InFileRepository<Route, String>  {

    public RouteRepository() {
        super("data/routes.json", Route[].class);
    }
}