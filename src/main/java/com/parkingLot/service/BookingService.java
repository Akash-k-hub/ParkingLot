package com.parkingLot.service;

import com.parkingLot.exception.InvalidDateTimeException;
import com.parkingLot.exception.ParkingFullException;
import com.parkingLot.model.Booking;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.model.User;
import com.parkingLot.repository.BookingRepository;
import com.parkingLot.repository.ParkingSlotRepository;
import com.parkingLot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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

    private LocalDateTime currentTime;
    private LocalDateTime arrivalTime;

    public Booking bookParkingSlot(Booking booking) throws ParkingFullException, InvalidDateTimeException {
        User user = userService.getUserByVehicleNumber(booking.getVehicleNumber());
        currentTime = LocalDateTime.now();
        arrivalTime = booking.getArrivalTime();
        LocalDateTime tempDateTime = arrivalTime.minusMinutes(15); //time 15 minutes before arrival
        Booking newBooking = new Booking();
        log.info("service=BookingService; method=bookParkingSlot");
        if (tempDateTime.isAfter(currentTime) || tempDateTime.isEqual(currentTime)) {
            if (user.isReservedUser()) {
                newBooking = bookReservedParking(booking);
            } else {
                newBooking = bookGeneralParking(booking);
            }
            log.info("service=BookingService; method=bookParkingSlot; parkingSlot {} booked", newBooking.getParkingNumber());
            return newBooking;
        }
        log.error("Exception in service=BookingService; method=bookParkingSlot; message=invalid date time entered");
        throw new InvalidDateTimeException("Invalid Date Time entered!");
    }

    public Booking bookGeneralParking(Booking booking) throws ParkingFullException {
        int generalParkingSlots = parkingService.getAvailableGeneralParkingSlots().size();
        User generalUser = userService.getUserByVehicleNumber(booking.getVehicleNumber());
        currentTime = LocalDateTime.now();
        arrivalTime = booking.getArrivalTime();
        if (generalParkingSlots > 0){
            log.info("service=BookingService; method=bookGeneralParking");
            ParkingSlot slot = parkingSlotRepository.getAvailableGeneralParkingSlots().get(0);
            //updating booking object of parkingSlot
            slot.getBooking().setUserName(generalUser.getName());
            slot.getBooking().setVehicleNumber(booking.getVehicleNumber());
            slot.getBooking().setBookingTime(currentTime);
            slot.getBooking().setArrivalTime(arrivalTime);
            //marking slot as booked and updating booking object
            slot.setSlotBooked(true);

            bookingRepository.save(slot.getBooking());
            parkingSlotRepository.save(slot);

            return slot.getBooking();
        }
        log.error("service=BookingService; method=bookParkingSlot; message=parking full");
        throw new ParkingFullException("Parking full!");
    }

    public Booking bookReservedParking(Booking booking) throws ParkingFullException {
        int reservedParkingSlots = parkingService.getAvailableReservedParkingSlots().size();
        User reserveUser = userService.getUserByVehicleNumber(booking.getVehicleNumber());
        currentTime = LocalDateTime.now();
        arrivalTime = booking.getArrivalTime();
        if (reservedParkingSlots > 0){
            log.info("service=BookingService; method=bookReservedParking");
            ParkingSlot slot = parkingSlotRepository.getAvailableReservedParkingSlots().get(0);
            slot.getBooking().setUserName(reserveUser.getName());
            slot.getBooking().setVehicleNumber(booking.getVehicleNumber());
            slot.getBooking().setBookingTime(currentTime);
            slot.getBooking().setArrivalTime(arrivalTime);
            //marking slot as booked and updating booking object
            slot.setSlotBooked(true);

            bookingRepository.save(slot.getBooking());
            parkingSlotRepository.save(slot);

            return slot.getBooking();
        }else{
            //Reserve parking is full
            log.warn("service=BookingService; method=bookReservedParking; message=reserveSlot are full, booking from generalSlot");
            return bookGeneralParking(booking);
        }
    }

    public List<Booking> getAllBookings(){
        log.info("service=BookingService; method=getAllBookings; message=retrieve list of all booking objects");
        return bookingRepository.findAll();
    }

}
