package com.parkingLot.controller;

import com.parkingLot.exception.InvalidDateTimeException;
import com.parkingLot.model.Parking;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @GetMapping("/getAllParkingSlots")
    public List<ParkingSlot> getParkingSlots(){
        return parkingService.getAllParkingSlot();
    }

    @PostMapping("/createParkingSlots")
    public ResponseEntity<String> initializeParkingSlots(){
        ResponseEntity<String> response = null;
        Parking result = parkingService.initializeParkingSlots();
        if(result != null){
            response = new ResponseEntity<String>("Parking Slots are created", HttpStatus.CREATED);
        }
        return response;
    }

//    count Occupied slots
    @GetMapping("/occupiedSlotsCount")
    public int countSlotsOccupied(){
        return parkingService.checkAvailableParkingSlots();
    }

    @GetMapping("/listAvailableSlots")
    public List<ParkingSlot> availableParkingSlots(){    /*GET all available parking slots*/
        return parkingService.getAvailableParkingSlots();
    }

    @GetMapping("/getBookedSlots")
    public List<ParkingSlot> bookedParkingSlots(){
        return parkingService.getBookedParkingSlots();
    }
}
