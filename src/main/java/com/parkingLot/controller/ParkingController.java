package com.parkingLot.controller;

import com.parkingLot.exception.InvalidDateTimeException;
import com.parkingLot.model.Parking;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.service.ParkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//private Logger log = LoggerFactory.getLogger(ParkingController.class);

@Slf4j
@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @GetMapping("/getAllParkingSlots")
    public List<ParkingSlot> getParkingSlots(){
        log.info("service=ParkingController; method=getParkingSlots; message=retrieve list of all slots");
        return parkingService.getAllParkingSlot();
    }

    @PostMapping("/createParkingSlots")
    public ResponseEntity<String> initializeParkingSlots(){
        ResponseEntity<String> response = null;
        log.info("service=ParkingController; method=initializeParkingSlots; message=initializing the parkingSlots");
        Parking result = parkingService.initializeParkingSlots();
        if(result != null){
            response = new ResponseEntity<String>("Parking Slots are created", HttpStatus.CREATED);
        }
        return response;
    }

//    count Occupied slots
    @GetMapping("/availableGeneralSlotsCount")
    public int countAvailableGeneralSlots(){
        log.info("service=ParkingController; method=countAvailableGeneralSlots; message=availableGeneralSlotCount");
        return parkingService.getAvailableGeneralParkingSlots().size();
    }

    @GetMapping("/listAvailableGeneralSlots")
    public List<ParkingSlot> availableParkingSlots(){    /*GET all available parking slots*/
        log.info("service=ParkingController; method=availableParkingSlots; message=list of available-general slots");
        return parkingService.getAvailableGeneralParkingSlots();
    }

    @GetMapping("/availableReservedSlotCount")
    public int countAvailableReservedSlots(){
        log.info("service=ParkingController; method=countAvailableReservedSlots; message=availableReservedSlotCount");
        return parkingService.getAvailableReservedParkingSlots().size();
    }

    @GetMapping("/listAvailableReservedSlots")
    public List<ParkingSlot> availableReservedParkingSlots(){
//        GET all available parking slots
        log.info("service=ParkingController; method=availableReservedParkingSlots; message=list of available-reserved slots");
        return parkingService.getAvailableReservedParkingSlots();
    }

    @GetMapping("/removeOutdatedBookings")
    public List<ParkingSlot> removeOutdatedParkingSlots(){
        log.info("service=ParkingController; method=removeOutdatedParkingSlots; message=removing the outdated slots");
        return parkingService.removeBookedParkingSlots();
    }

    @GetMapping("/getAvailableParkingSlots")
    public List<ParkingSlot> getAvailableParkingSlots(){
        log.info("service=ParkingController; method=getAvailableParkingSlots; message=list of all available slots");
        return parkingService.getAvailableParkingSlots();
    }

    @GetMapping("/getBookedParkingSlots")
    public List<ParkingSlot> getBookedParkingSlots(){
        log.info("service=ParkingController; method=getBookedParkingSlots; message=list of all booked slots");
        return parkingService.getBookedParkingSlots();
    }
}
