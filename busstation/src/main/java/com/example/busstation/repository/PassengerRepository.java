package com.example.busstation.repository;

import com.example.busstation.model.Passenger;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerRepository extends InFileRepository<Passenger, String> implements IPassengerRepository {

    public PassengerRepository() {
        super("data/passengers.json", Passenger[].class);
    }
}