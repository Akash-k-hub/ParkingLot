package com.parkingLot.repository;

import com.parkingLot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{\"vehicleNumber\" : \"?0\"}")
    User validateVehicleNumber(String vehicleNumber);
}
