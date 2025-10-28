package com.example.busstation.controller;

import com.example.busstation.model.BusStation;
import com.example.busstation.service.BusStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/busstations")
public class BusStationController extends AbstractBaseController {

    private final BusStationService busStationService;

    public BusStationController(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @GetMapping
    public List<BusStation> getAllBusStations() {
        return busStationService.getAllBusStations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusStation> getBusStationById(@PathVariable String id) {
        Optional<BusStation> busStation = busStationService.getBusStationById(id);
        return busStation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusStation createBusStation(@RequestBody BusStation busStation) {
        return busStationService.createBusStation(busStation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBusStation(@PathVariable String id) {
        busStationService.deleteBusStation(id);
    }
}
