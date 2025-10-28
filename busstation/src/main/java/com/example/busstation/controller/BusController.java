package com.example.busstation.controller;

import com.example.busstation.model.Bus;
import com.example.busstation.service.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Wichtig: Viele neue Imports!

import java.util.List;
import java.util.Optional;

// 1. Zu @RestController ändern, um JSON-Daten zu senden
@RestController
// 2. Ein Basis-Mapping. Alle URLs hier beginnen mit /api/buses
@RequestMapping("/api/buses")
public class BusController extends AbstractBaseController { // Eure Vererbung bleibt erhalten

    // 3. Der Controller braucht den Service
    private final BusService busService;

    // 4. Dependency Injection für den Service
    public BusController(BusService busService) {
        this.busService = busService;
    }

    /**
     * Holt alle Busse.
     * Mapped auf: GET http://localhost:8080/api/buses
     */
    @GetMapping
    public List<Bus> getAllBuses() {
        // Ruft euren BusService auf
        return busService.getAllBusse();
    }

    /**
     * Holt einen einzelnen Bus anhand seiner ID.
     * Mapped auf: GET http://localhost:8080/api/buses/{id} (z.B. /api/buses/1)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable String id) {
        Optional<Bus> bus = busService.getBusById(id);

        // Dies gibt 200 OK zurück, wenn der Bus gefunden wurde,
        // oder 404 Not Found, wenn nicht.
        return bus.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Erstellt einen neuen Bus.
     * Mapped auf: POST http://localhost:8080/api/buses
     * @param bus Das Bus-Objekt kommt als JSON im Request Body.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Setzt den HTTP-Status auf 201 Created
    public Bus createBus(@RequestBody Bus bus) {
        // Ruft euren Service auf, um den Bus zu speichern
        return busService.createBus(bus);
    }

    /**
     * Löscht einen Bus.
     * Mapped auf: DELETE http://localhost:8080/api/buses/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Setzt den HTTP-Status auf 204 No Content
    public void deleteBus(@PathVariable String id) {
        busService.deleteBus(id);
    }
}