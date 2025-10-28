package com.example.busstation;

import com.example.busstation.model.Bus;
import com.example.busstation.model.Passenger;
import com.example.busstation.service.BusService;
import com.example.busstation.service.PassengerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Diese Klasse wird automatisch beim Start von Spring Boot ausgeführt.
 * Sie dient dazu, unsere In-Memory-Repositories mit Testdaten zu füllen.
 */
@Component // WICHTIG: Sagt Spring, dass es diese Klasse verwalten soll
public class DataInitializer implements CommandLineRunner {

    // Wir injizieren die Services (nicht die Repositories),
    // da dies die korrekte Schichtentrennung ist.
    private final BusService busService;
    private final PassengerService passengerService;
    // Fügt hier weitere Services hinzu (z.B. RouteService)

    public DataInitializer(BusService busService, PassengerService passengerService) {
        this.busService = busService;
        this.passengerService = passengerService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("--- LADE TESTDATEN ---");

        // 1. Erstelle ein paar Busse
        Bus bus1 = new Bus();
        bus1.setName("CJ-01-ABC"); // In Bus.java setzt setName() die RegistrationNumber
        bus1.setCapacity(50);
        bus1.setStatus("Active");
        busService.createBus(bus1); // Der Service ruft das Repo auf -> ID wird gesetzt

        Bus bus2 = new Bus();
        bus2.setName("B-100-XYZ");
        bus2.setCapacity(30);
        bus2.setStatus("In Maintenance");
        busService.createBus(bus2);

        System.out.println(busService.getAllBusse().size() + " Busse geladen.");

        // 2. Erstelle ein paar Passagiere
        Passenger p1 = new Passenger();
        p1.setName("Max Mustermann");
        p1.setCurrency("EUR");
        p1.setAge(30);
        passengerService.createPassenger(p1);

        Passenger p2 = new Passenger();
        p2.setName("Erika Mustermann");
        p2.setCurrency("USD");
        p2.setAge(28);
        passengerService.createPassenger(p2);

        System.out.println(passengerService.getAllPassengers().size() + " Passagiere geladen.");

        // 3. Fügt hier weitere Daten hinzu (Routen, BusStations etc.)

        System.out.println("--- TESTDATEN GELADEN ---");
    }
}
