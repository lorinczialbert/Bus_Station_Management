package com.example.busstation.repository;

import com.example.busstation.model.BusStation;
import org.springframework.stereotype.Repository;

// 1. Implementează interfața și moștenește InFileRepository
@Repository
public class BusStationRepository extends InFileRepository<BusStation, String> implements IBusStationRepository {

    public BusStationRepository() {
        // 2. Specifică fișierul JSON și tipul clasei
        super("data/busstations.json", BusStation[].class);
    }
}