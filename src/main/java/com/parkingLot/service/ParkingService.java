package com.parkingLot.service;

import com.parkingLot.model.Parking;
import com.parkingLot.repository.ParkingRepository;
import com.parkingLot.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    public Parking createParking(Parking parking){
        if(parking.getParkingSlot() != null && parking.getParkingSlot().size() > 0){
            parkingSlotRepository.saveAll(parking.getParkingSlot());
        }
        return parkingRepository.save(parking);
    }
}
