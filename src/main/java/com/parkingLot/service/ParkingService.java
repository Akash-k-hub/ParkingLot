package com.parkingLot.service;

import com.parkingLot.constants.Constant;
import com.parkingLot.model.Booking;
import com.parkingLot.model.Parking;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.repository.BookingRepository;
import com.parkingLot.repository.ParkingRepository;
import com.parkingLot.repository.ParkingSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParkingService {

    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    ParkingSlotRepository parkingSlotRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<ParkingSlot> getAllParkingSlot(){
        //    returns the list of all parkingSlot - booked, unbooked, reserved, general
        log.info("service=ParkingService; method=getAllParkingSlot; message=retrieve list of all slots");
        return parkingSlotRepository.findAll();
    }


    public List<ParkingSlot> getAvailableReservedParkingSlots(){
        //   return count of all available reserved parking slot
        log.info("service=ParkingService; method=getAvailableReservedParkingSlots; message=list of available-reserved slots");
        return parkingSlotRepository.getAvailableReservedParkingSlots();
    }

    public List<ParkingSlot> getAvailableGeneralParkingSlots(){
        //    returns list of all available parking slot
        log.info("service=ParkingService; method=getAvailableGeneralParkingSlots; message=list of available-general slots");
        return parkingSlotRepository.getAvailableGeneralParkingSlots();
    }

    public List<ParkingSlot> getAvailableParkingSlots(){
        //      returns list of all available slots (both reserved & general)
        log.info("service=ParkingService; method=getAvailableParkingSlots; message=list of all available slots");
        return parkingSlotRepository.getAvailableParkingSlots();
    }

    public List<ParkingSlot> getBookedParkingSlots(){
        //      returns list of all booked parking slots(both reserved & general)
        log.info("service=ParkingService; method=getBookedParkingSlots; message=list of all booked slots");
        return parkingSlotRepository.getBookedParkingSlots();
    }

    public Parking initializeParkingSlots(){
        //    initialize the parkingSlot in the DB
        Parking parking = new Parking();
        List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
        log.info("service=ParkingService; method=initializeParkingSlots; message=initializing the parkingSlots");
        for (int i = 0; i < Constant.PARKING_CAPACITY; i++){
            ParkingSlot slot = new ParkingSlot();
            Booking booking = new Booking();

            //initialising default parkingSlot fields
            slot.setArrivalTime(Constant.DEFAULT_DATE_TIME);
            slot.setParkingNumber(i+1);
            slot.setSlotReserved(false);
            slot.setSlotBooked(false);
            slot.setSlotOccupied(false);

            //initialising default booking fields
            booking.setUserName("");
            booking.setVehicleNumber("");
            booking.setBookingTime(Constant.DEFAULT_DATE_TIME);
            booking.setArrivalTime(Constant.DEFAULT_DATE_TIME);
            booking.setParkingNumber(i+1);

            slot.setBooking(booking);

            bookingRepository.save(booking);
            parkingSlots.add(slot);
            parkingSlotRepository.save(slot);
        }
        parking.setParkingSlot(parkingSlots);
        log.info("service=ParkingService; method=initializeParkingSlots; message=parkingSlots created");
        return parkingRepository.save(parking);
    }

    public List<ParkingSlot> removeBookedParkingSlots(){
        log.warn("service=ParkingService; method=removeBookedParkingSlots; message=removing the outdated slots");
//        reservedSlots that are not booked
        int reservedCapacity = parkingSlotRepository.getAvailableReservedParkingSlots().size();
//        generalSlots that are not booked
        int generalCapacity = parkingSlotRepository.getAvailableGeneralParkingSlots().size();
        long waitMinutes;

        if ((reservedCapacity > (Constant.RESERVED_PARKING_CAPACITY/2))
                || (generalCapacity > (Constant.GENERAL_PARKING_CAPACITY/2))) {
            waitMinutes = 15L;
        }else {
            waitMinutes = 30L;
        }

        // reverting back the outdated slots and bookings to default
        Query query = new Query();
        query.addCriteria(Criteria.where("slotBooked").is(true));
        List<ParkingSlot> slots = new ArrayList<>();
        //list of outdated booked slots
        slots = mongoTemplate.find(query, ParkingSlot.class).stream()
                    .filter(s -> s.getBooking().getArrivalTime().minusMinutes(-waitMinutes).isBefore(LocalDateTime.now()))
                    .collect(Collectors.toList());

        for ( ParkingSlot slot : slots){
            slot.setSlotBooked(false);
            slot.getBooking().setBookingTime(Constant.DEFAULT_DATE_TIME);
            slot.getBooking().setArrivalTime(Constant.DEFAULT_DATE_TIME);
            slot.getBooking().setUserName("");
            slot.getBooking().setVehicleNumber("");

            bookingRepository.save(slot.getBooking());
            parkingSlotRepository.save(slot);
        }
        log.info("service=ParkingService; method=removeBookedParkingSlots; message={} slot(s) got removed", slots.size());
        return slots;
    }
}
