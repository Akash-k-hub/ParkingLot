package com.parkingLot.repository;

import com.parkingLot.model.ParkingSlot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, String> {

    @Query("{'slotOccupied':false, 'slotBooked':false, 'slotReserved':false}")
    ArrayList<ParkingSlot> getAvailableGeneralParkingSlots();

    @Query("{'slotReserved' : true, 'slotBooked':false}")
    ArrayList<ParkingSlot> getAvailableReservedParkingSlots();

    @Query("{'slotBooked':false}")
    ArrayList<ParkingSlot> getAvailableParkingSlots();

    @Query("{'slotBooked':true}")
    ArrayList<ParkingSlot> getBookedParkingSlots();
}
