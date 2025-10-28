package com.example.busstation.controller;

// Importiert alle Modelle
import com.example.busstation.model.*;
// Importiert alle Services
import com.example.busstation.service.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Diese Klasse wird automatisch beim Start von Spring Boot ausgeführt.
 * Sie dient dazu, unsere In-Memory-Repositories mit Testdaten zu füllen.
 */
@Component // WICHTIG: Sagt Spring, dass es diese Klasse verwalten soll
public class DataInitializer implements CommandLineRunner {

    // 1. Alle Services injizieren
    private final BusService busService;
    private final PassengerService passengerService;
    private final BusStationService busStationService;
    private final RouteService routeService;
    private final StaffService staffService;
    private final BusTripService busTripService;
    private final TicketService ticketService;
    private final DutyAssignmentService dutyAssignmentService;

    // Konstruktor, der alle Services per Dependency Injection erhält
    public DataInitializer(BusService busService, PassengerService passengerService,
                           BusStationService busStationService, RouteService routeService,
                           StaffService staffService, BusTripService busTripService,
                           TicketService ticketService, DutyAssignmentService dutyAssignmentService) {
        this.busService = busService;
        this.passengerService = passengerService;
        this.busStationService = busStationService;
        this.routeService = routeService;
        this.staffService = staffService;
        this.busTripService = busTripService;
        this.ticketService = ticketService;
        this.dutyAssignmentService = dutyAssignmentService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("--- LADE TESTDATEN ---");

        // 2. Daten in logischer Reihenfolge erstellen (unabhängige zuerst)

        // BusStations (sind unabhängig)
        BusStation stationA = new BusStation();
        stationA.setName("Hauptbahnhof");
        stationA.setCity("Berlin");
        busStationService.createBusStation(stationA); // Service speichert -> A bekommt ID "1"

        BusStation stationB = new BusStation();
        stationB.setName("ZOB West");
        stationB.setCity("Hamburg");
        busStationService.createBusStation(stationB); // Service speichert -> B bekommt ID "2"

        // Routen (brauchen BusStations)
        Route route1 = new Route();
        route1.setOrigin(stationA); // Verwendet das ganze Objekt
        route1.setDestination(stationB);
        route1.setDistance(280.5);
        routeService.createRoute(route1); // ID "1"

        // Busse (sind unabhängig)
        Bus bus1 = new Bus();
        bus1.setName("B-123-GO"); // Setzt RegistrationNumber
        bus1.setCapacity(50);
        bus1.setStatus("Active");
        bus1.setFactoryDate(LocalDate.of(2020, 5, 10)); // Extra-Attribut
        busService.createBus(bus1); // ID "1"

        // Passagiere (sind unabhängig)
        Passenger p1 = new Passenger();
        p1.setName("Max Mustermann");
        p1.setCurrency("EUR");
        p1.setAge(30);
        passengerService.createPassenger(p1); // ID "1"

        // Staff (sind unabhängig, wir erstellen Unterklassen)
        Driver driver1 = new Driver();
        driver1.setStaffName("Klaus Fahrer");
        driver1.setYearsOfExperience(10);
        staffService.createStaff(driver1); // ID "1"

        TripManager manager1 = new TripManager();
        manager1.setStaffName("Anna Managerin");
        manager1.setEmployeeCode("MGR-001");
        staffService.createStaff(manager1); // ID "2"

        // BusTrip (braucht BusId und RouteId)
        BusTrip trip1 = new BusTrip();
        trip1.setBusId(bus1.getId()); // "1"
        trip1.setRouteId(route1.getId()); // "1"
        trip1.setStartTime(LocalDateTime.now().plusHours(3).toString()); // UML sagt String
        trip1.setStatus("Planned");
        trip1.setTickets(new ArrayList<>()); // Listen initialisieren
        trip1.setAssignments(new ArrayList<>());
        busTripService.createBusTrip(trip1); // ID "1"

        // Ticket (braucht TripId und PassengerId)
        Ticket ticket1 = new Ticket();
        ticket1.setTripID(trip1.getId()); // "1"
        ticket1.setPassengerID(p1.getId()); // "1"
        ticket1.setSeatNumber("7A");
        ticket1.setPrice(49.99);
        ticketService.createTicket(ticket1); // ID "1"

        // DutyAssignment (braucht TripId und StaffId)
        DutyAssignment da1 = new DutyAssignment();
        da1.setTripId(trip1.getId()); // "1"
        da1.setStaffId(driver1.getStaffId()); // "1"
        da1.setRole("Primary Driver");
        dutyAssignmentService.createAssignment(da1); // ID "1"

        // DutyAssignment (für den Manager)
        DutyAssignment da2 = new DutyAssignment();
        da2.setTripId(trip1.getId()); // "1"
        da2.setStaffId(manager1.getStaffId()); // "2"
        da2.setRole("Supervisor");
        dutyAssignmentService.createAssignment(da2); // ID "2"


        System.out.println("--- TESTDATEN GELADEN ---");
        System.out.println(busStationService.getAllBusStations().size() + " BusStations geladen.");
        System.out.println(routeService.getAllRoutes().size() + " Routen geladen.");
        System.out.println(busService.getAllBusse().size() + " Busse geladen.");
        System.out.println(passengerService.getAllPassengers().size() + " Passagiere geladen.");
        System.out.println(staffService.getAllStaff().size() + " Staff-Mitglieder geladen.");
        System.out.println(busTripService.getAllBusTrips().size() + " BusTrips geladen.");
        System.out.println(ticketService.getAllTickets().size() + " Tickets geladen.");
        System.out.println(dutyAssignmentService.getAllAssignments().size() + " Assignments geladen.");
    }
}

