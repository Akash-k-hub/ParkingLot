package com.parkingLot.controller;

import com.parkingLot.model.Parking;
import com.parkingLot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping("/createParking")
    public Parking saveParking(@RequestBody Parking parking){
        return parkingService.createParking(parking);
    }
}
