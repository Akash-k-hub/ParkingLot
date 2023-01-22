package com.parkingLot.application;

import com.parkingLot.exception.InvalidDateTimeException;
import com.parkingLot.exception.ParkingFullException;
import com.parkingLot.model.Booking;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.model.User;
import com.parkingLot.repository.BookingRepository;
import com.parkingLot.repository.ParkingSlotRepository;
import com.parkingLot.repository.UserRepository;
import com.parkingLot.service.BookingService;
import com.parkingLot.service.ParkingService;
import com.parkingLot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    private BookingService bookingService;
    @MockBean
    private BookingRepository bookingRepository;
    @Autowired
    private ParkingService parkingService;
    @MockBean
    private ParkingSlotRepository parkingSlotRepository;
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void bookParkingTest() throws ParkingFullException, InvalidDateTimeException {
        ParkingSlot slot = new ParkingSlot();
        Booking booking = new Booking();

        List<ParkingSlot> slotList = new ArrayList<>();
        ParkingSlot slot2 = new ParkingSlot();
        slot2.setBooking(new Booking());
        slotList.add(slot2);

        User user = new User("123", "Akash", "pswd", "9874563210", "UP32AB0000", false);

        booking.setBookingTime(LocalDateTime.now());
        booking.setArrivalTime(LocalDateTime.now().minusMinutes(-100));
        booking.setVehicleNumber(user.getVehicleNumber());
        booking.setUserName(user.getName());
        booking.setParkingNumber(1);
        booking.setId("abcgdtej7890");

        slot.setSlotReserved(false);
        slot.setSlotBooked(false);
        slot.setParkingNumber(1);
        slot.setId("abxdks1254");
        slot.setSlotLocked(false);
        slot.setArrivalTime(LocalDateTime.now().minusMinutes(100));
        slot.setBooking(booking);

        userRepository.save(user);
        when(userService.getUserByVehicleNumber(booking.getVehicleNumber())).thenReturn(user);
        when(parkingService.getAvailableGeneralParkingSlots()).thenReturn(slotList);
        when(parkingSlotRepository.save(slot)).thenReturn(slot);

        assertEquals("Akash", bookingService.bookParkingSlot(booking).getUserName());
        assertEquals("UP32AB0000", bookingService.bookParkingSlot(booking).getVehicleNumber());
    }

    @Test
    public void getAllBookingsTest(){
        List<Booking> bookingList = new ArrayList<>();
        Booking booking1 = new Booking("testID1", "Tester1", "TC55BB2314", LocalDateTime.now(), LocalDateTime.now(), 1);

        bookingList.add(booking1);
        bookingList.add(new Booking("testID2", "Tester2", "TC55BB2222", LocalDateTime.now(), LocalDateTime.now(), 2));
        bookingList.add(new Booking("testID3", "Tester3", "TC55BB3333", LocalDateTime.now(), LocalDateTime.now(), 3));
        bookingList.add(new Booking("testID4", "Tester4", "TC55BB4444", LocalDateTime.now(), LocalDateTime.now(), 4));

        when(bookingRepository.findAll()).thenReturn(bookingList);
        assertEquals(4, bookingService.getAllBookings().size());
        assertEquals(booking1, bookingService.getAllBookings().get(0));
    }

}
