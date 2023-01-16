package com.parkingLot.service;

import com.parkingLot.exception.InvalidDateTimeException;
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


//    returns the list of all parkingSlot -
    public List<ParkingSlot> getAllParkingSlot(){
        return parkingSlotRepository.findAll();
    }

//    returns count of all parkingSlots
    public int parkingSlotCount(){
        return parkingSlotRepository.findAll().size();
    }

//    initialize the parkingSlot in the DB
    public Parking initializeParkingSlots(){
        Parking parking = new Parking();
        List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();

        for (int i = 0; i < 120; i++){
            ParkingSlot slot = new ParkingSlot();
            Booking booking = new Booking();

            //initialising default parkingSlot fields
            slot.setArrivalTime(LocalDateTime.now());
            slot.setParkingNumber(i+1);
            slot.setSlotReserved(false);
            slot.setSlotBooked(false);
            slot.setSlotOccupied(false);

            //initialising default booking fields
            booking.setUserName("");
            booking.setVehicleNumber("");
            booking.setBookingTime(LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0, 0));
            booking.setArrivalTime(LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0, 0));
            booking.setParkingNumber(i+1);

            slot.setBooking(booking);

            bookingRepository.save(booking);
            parkingSlots.add(slot);
            parkingSlotRepository.save(slot);
        }
        parking.setParkingSlot(parkingSlots);
        return parkingRepository.save(parking);
    }

//   return count of all available parking slot
    public int checkAvailableParkingSlots(){
        return parkingSlotRepository.getAllAvailableParkingSlots().size();
    }

//    returns list of all available parking slot
    public List<ParkingSlot> getAvailableParkingSlots(){
        return parkingSlotRepository.getAllAvailableParkingSlots();
    }

    public List<ParkingSlot> getBookedParkingSlots(){

        Query query = new Query();
        query.addCriteria(Criteria.where("slotBooked").is(true));
        List<ParkingSlot> slots = mongoTemplate.find(query, ParkingSlot.class); //list of all booked slots
        return slots.stream().filter(s -> s.getBooking().getArrivalTime().minusMinutes(-30).isAfter(LocalDateTime.now())).collect(Collectors.toList());

//        return null;
    }
}
