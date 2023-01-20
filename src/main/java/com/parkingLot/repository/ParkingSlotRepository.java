package com.parkingLot.repository;

import com.parkingLot.model.ParkingSlot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, String> {

    @Query("{'slotBooked':false, 'slotReserved':false, 'slotLocked':false}")
    ArrayList<ParkingSlot> getAvailableGeneralParkingSlots();

    @Query("{'slotBooked':false, 'slotReserved' : true}")
    ArrayList<ParkingSlot> getAvailableReservedParkingSlots();

    @Query("{'slotBooked':false}")
    ArrayList<ParkingSlot> getAvailableParkingSlots();

    @Query("{'slotBooked':true}")
    ArrayList<ParkingSlot> getBookedParkingSlots();
}
