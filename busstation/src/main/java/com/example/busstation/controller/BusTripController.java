package com.example.busstation.controller;

import com.example.busstation.model.BusTrip;
import com.example.busstation.service.BusTripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bustrips")
public class BusTripController extends AbstractBaseController {

    private final BusTripService busTripService;

    public BusTripController(BusTripService busTripService) {
        this.busTripService = busTripService;
    }

    @GetMapping
    public List<BusTrip> getAllBusTrips() {
        return busTripService.getAllBusTrips();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusTrip> getBusTripById(@PathVariable String id) {
        Optional<BusTrip> busTrip = busTripService.getBusTripById(id);
        return busTrip.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusTrip createBusTrip(@RequestBody BusTrip busTrip) {
        return busTripService.createBusTrip(busTrip);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBusTrip(@PathVariable String id) {
        busTripService.deleteBusTrip(id);
    }
}