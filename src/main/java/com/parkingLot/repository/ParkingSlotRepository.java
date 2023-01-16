package com.parkingLot.repository;

import com.parkingLot.model.ParkingSlot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, String> {

    @Query("{'slotOccupied':false, 'slotBooked':false}")
    ArrayList<ParkingSlot> getAllAvailableParkingSlots();

}
