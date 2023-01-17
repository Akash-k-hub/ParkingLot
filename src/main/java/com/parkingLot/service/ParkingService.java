package com.parkingLot.service;

import com.parkingLot.constants.Constant;
import com.parkingLot.model.Booking;
import com.parkingLot.model.Parking;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.repository.BookingRepository;
import com.parkingLot.repository.ParkingRepository;
import com.parkingLot.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        //    returns the list of all parkingSlot -
        return parkingSlotRepository.findAll();
    }

    public int parkingSlotCount(){
        //    returns count of all parkingSlots
        return parkingSlotRepository.findAll().size();
    }

    public Parking initializeParkingSlots(){
        //    initialize the parkingSlot in the DB
        Parking parking = new Parking();
        List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();

        for (int i = 0; i < 120; i++){
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
        return parkingRepository.save(parking);
    }


    /*public int checkAvailableParkingSlots(){
        //   return count of all available parking slot
        return parkingSlotRepository.getAvailableGeneralParkingSlots().size();
    }

     */

    public List<ParkingSlot> getAvailableReservedParkingSlots(){
        //   return count of all available reserved parking slot
        return parkingSlotRepository.getAvailableReservedParkingSlots();
    }


    public List<ParkingSlot> getAvailableGeneralParkingSlots(){
        //    returns list of all available parking slot
        return parkingSlotRepository.getAvailableGeneralParkingSlots();
    }

    public List<ParkingSlot> getBookedParkingSlots(){
        int reservedCapacity = parkingSlotRepository.getAvailableReservedParkingSlots().size();
        int generalCapacity = parkingSlotRepository.getAvailableGeneralParkingSlots().size();
        long waitMinutes;

        if ((reservedCapacity > 12) || (generalCapacity > 48)) {
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
        return slots;
    }
}
