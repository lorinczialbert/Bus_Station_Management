package com.example.busstation.repository;

import com.example.busstation.model.BusTrip;
import org.springframework.stereotype.Repository;

@Repository
public class BusTripRepository extends InFileRepository<BusTrip, String> implements IBusTripRepository {

    public BusTripRepository() {
        super("data/bustrips.json", BusTrip[].class);
    }
}