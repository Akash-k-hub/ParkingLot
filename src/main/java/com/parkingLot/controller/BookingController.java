package com.parkingLot.controller;

import com.parkingLot.exception.InvalidDateTimeException;
import com.parkingLot.exception.ParkingFullException;
import com.parkingLot.model.Booking;
import com.parkingLot.model.User;
import com.parkingLot.service.BookingService;
import com.parkingLot.service.ParkingService;
import com.parkingLot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private UserService userService;

    @GetMapping("/getAllBookings")
    public ResponseEntity<List<Booking>> getAllBooking(){
        ResponseEntity<List<Booking>> response;
        log.info("service=BookingController; method=getAllBooking");
        List<Booking> bookingList = bookingService.getAllBookings();
        response = new ResponseEntity<>(bookingList, HttpStatus.OK);

        return response;
    }

    @PostMapping("/bookParkingSlot")
    public ResponseEntity<Booking> bookParkingSlot(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Booking booking)
            throws ParkingFullException, InvalidDateTimeException {
        ResponseEntity<Booking> response;
        Booking result = bookingService.bookParkingSlot(booking);
        log.info("service=BookingController; method=bookParkingSlot");
        response = new ResponseEntity<>(result, HttpStatus.CREATED);

        return response;
    }
}
