package com.parkingLot.service;

import com.parkingLot.exception.InvalidDateTimeException;
import com.parkingLot.exception.ParkingFullException;
import com.parkingLot.model.Booking;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.model.User;
import com.parkingLot.repository.BookingRepository;
import com.parkingLot.repository.ParkingSlotRepository;
import com.parkingLot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParkingSlotRepository parkingSlotRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ParkingService parkingService;


    public Booking bookParkingSlot(Booking booking) throws ParkingFullException, InvalidDateTimeException{

        int availableParkingSlots = parkingService.checkAvailableParkingSlots();
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime arrivalTime = booking.getArrivalTime();
        //time 15 minutes before arrival
        LocalDateTime tempDateTime = arrivalTime.minusMinutes(15);

        if(tempDateTime.isAfter(currentTime) || tempDateTime.isEqual(currentTime)){
            if(availableParkingSlots > 0){
                ParkingSlot slot = parkingSlotRepository.getAllAvailableParkingSlots().get(0);
                //Booking newBooking = new Booking();
                User user = userService.getUserByVehicleNumber(booking.getVehicleNumber());
                //updating booking object of parkingSlot
                slot.getBooking().setUserName(user.getName());
                slot.getBooking().setVehicleNumber(booking.getVehicleNumber());
                slot.getBooking().setBookingTime(currentTime);
                slot.getBooking().setArrivalTime(arrivalTime);

                //marking get(0)th slot as booked and updating booking object
                slot.setSlotBooked(true);
                bookingRepository.save(slot.getBooking());

                parkingSlotRepository.save(slot);

                return slot.getBooking();
            }
            throw new ParkingFullException("Parking Full!");
        }
        throw new InvalidDateTimeException("Invalid Date Time entered!");
    }

}
