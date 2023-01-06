package com.parkingLot.repository;

import com.parkingLot.model.ParkingSlot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, String> {
}
