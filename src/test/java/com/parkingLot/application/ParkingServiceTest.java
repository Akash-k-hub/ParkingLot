package com.parkingLot.application;

import com.parkingLot.model.Booking;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.repository.BookingRepository;
import com.parkingLot.repository.ParkingSlotRepository;
import com.parkingLot.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class ParkingServiceTest {

    @Autowired
    private ParkingService parkingService;
    @MockBean
    private ParkingSlotRepository parkingSlotRepository;
    @MockBean
    private BookingRepository bookingRepository;

    @Test
    public void getAllParkingSlotTest(){
        List<ParkingSlot> slotList = new ArrayList<>();
        ParkingSlot slot1 = new ParkingSlot();
        ParkingSlot slot2 = new ParkingSlot();

        slot1.setParkingNumber(1);
        slot1.setSlotReserved(true);
        slot1.setSlotBooked(false);
        slot1.setId("testID");
        slot1.setBooking(new Booking("bookTestID", "Tester1", "TC55TP0000", LocalDateTime.now(), LocalDateTime.now(), 1));
        slot1.setArrivalTime(LocalDateTime.now());

        slot2.setParkingNumber(2);
        slot2.setSlotReserved(true);
        slot2.setSlotBooked(true);
        slot2.setId("testID2");
        slot2.setBooking(new Booking("bookTestID2", "Tester2", "TC55TP1111", LocalDateTime.now(), LocalDateTime.now(), 2));
        slot2.setArrivalTime(LocalDateTime.now());

        slotList.add(slot1);
        slotList.add(slot2);

        when(parkingSlotRepository.findAll()).thenReturn(slotList);
        assertEquals(2, parkingService.getAllParkingSlot().size());
        assertEquals("Tester1", parkingService.getAllParkingSlot().get(0).getBooking().getUserName());
        assertEquals("TC55TP0000", parkingService.getAllParkingSlot().get(0).getBooking().getVehicleNumber());
        assertEquals("Tester2", parkingService.getAllParkingSlot().get(1).getBooking().getUserName());
        assertEquals("TC55TP1111", parkingService.getAllParkingSlot().get(1).getBooking().getVehicleNumber());
    }

    @Test
    public void getAvailableReservedParkingSlotsTest(){
        ArrayList<ParkingSlot> slotList = new ArrayList<>();
        ParkingSlot slot1 = new ParkingSlot();
        ParkingSlot slot2 = new ParkingSlot();
        ParkingSlot slot3 = new ParkingSlot();
        ParkingSlot slot4 = new ParkingSlot();

        slot1.setParkingNumber(1);
        slot1.setSlotReserved(true);
        slot1.setSlotBooked(false);
        slot1.setId("testID");
        slot1.setBooking(new Booking("bookTestID", "Tester1", "TC55TP1111", LocalDateTime.now(), LocalDateTime.now(), 1));
        slot1.setArrivalTime(LocalDateTime.now());

        slot2.setParkingNumber(2);
        slot2.setSlotReserved(true);
        slot2.setSlotBooked(false);
        slot2.setId("testID2");
        slot2.setBooking(new Booking("bookTestID2", "Tester2", "TC55TP2222", LocalDateTime.now(), LocalDateTime.now(), 2));
        slot2.setArrivalTime(LocalDateTime.now());

        slot3.setParkingNumber(3);
        slot3.setSlotReserved(true);
        slot3.setSlotBooked(false);
        slot3.setId("testID3");
        slot3.setBooking(new Booking("bookTestID3", "Tester3", "TC55TP3333", LocalDateTime.now(), LocalDateTime.now(), 3));
        slot3.setArrivalTime(LocalDateTime.now());

        slot4.setParkingNumber(4);
        slot4.setSlotReserved(true);
        slot4.setSlotBooked(false);
        slot4.setId("testID4");
        slot4.setBooking(new Booking("bookTestID4", "Tester4", "TC55TP4444", LocalDateTime.now(), LocalDateTime.now(), 4));
        slot4.setArrivalTime(LocalDateTime.now());

        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        when(parkingSlotRepository.getAvailableReservedParkingSlots()).thenReturn(slotList);
        assertEquals(4, parkingService.getAvailableReservedParkingSlots().size());
        assertTrue((
                parkingService.getAvailableReservedParkingSlots().get(0).isSlotReserved()==true &&
                        parkingService.getAvailableReservedParkingSlots().get(0).isSlotBooked()==false
                ), "These are available reserved slots");
        assertTrue((
                parkingService.getAvailableReservedParkingSlots().get(1).isSlotReserved()==true &&
                        parkingService.getAvailableReservedParkingSlots().get(1).isSlotBooked()==false
        ), "These are available reserved slots");
        assertTrue((
                parkingService.getAvailableReservedParkingSlots().get(2).isSlotReserved()==true &&
                        parkingService.getAvailableReservedParkingSlots().get(2).isSlotBooked()==false
        ), "These are available reserved slots");
        assertTrue((
                parkingService.getAvailableReservedParkingSlots().get(3).isSlotReserved()==true &&
                        parkingService.getAvailableReservedParkingSlots().get(3).isSlotBooked()==false
        ), "These are available reserved slots");
    }

    @Test
    public void getAvailableGeneralParkingSlotsTest(){
        ArrayList<ParkingSlot> slotList = new ArrayList<>();
        ParkingSlot slot1 = new ParkingSlot();
        ParkingSlot slot2 = new ParkingSlot();
        ParkingSlot slot3 = new ParkingSlot();

        slot1.setParkingNumber(1);
        slot1.setSlotReserved(false);
        slot1.setSlotBooked(false);
        slot1.setId("testID");
        slot1.setBooking(new Booking("bookTestID", "Tester1", "TC55TP1111", LocalDateTime.now(), LocalDateTime.now(), 1));
        slot1.setArrivalTime(LocalDateTime.now());

        slot2.setParkingNumber(2);
        slot2.setSlotReserved(false);
        slot2.setSlotBooked(false);
        slot2.setId("testID2");
        slot2.setBooking(new Booking("bookTestID2", "Tester2", "TC55TP2222", LocalDateTime.now(), LocalDateTime.now(), 2));
        slot2.setArrivalTime(LocalDateTime.now());

        slot3.setParkingNumber(3);
        slot3.setSlotReserved(false);
        slot3.setSlotBooked(false);
        slot3.setId("testID3");
        slot3.setBooking(new Booking("bookTestID3", "Tester3", "TC55TP3333", LocalDateTime.now(), LocalDateTime.now(), 3));
        slot3.setArrivalTime(LocalDateTime.now());


        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        when(parkingSlotRepository.getAvailableGeneralParkingSlots()).thenReturn(slotList);
        assertEquals(3, parkingService.getAvailableGeneralParkingSlots().size());
        assertTrue((
                parkingService.getAvailableGeneralParkingSlots().get(0).isSlotBooked()==false &&
                        parkingService.getAvailableGeneralParkingSlots().get(0).isSlotReserved()==false),
                "This is Available General slot");
        assertTrue((
                        parkingService.getAvailableGeneralParkingSlots().get(1).isSlotBooked()==false &&
                                parkingService.getAvailableGeneralParkingSlots().get(1).isSlotReserved()==false),
                "This is Available General slot");
        assertTrue((
                        parkingService.getAvailableGeneralParkingSlots().get(2).isSlotBooked()==false &&
                                parkingService.getAvailableGeneralParkingSlots().get(2).isSlotReserved()==false),
                "This is Available General slot");
    }

    @Test
    public void getAvailableParkingSlotsTest(){
        ArrayList<ParkingSlot> slotList = new ArrayList<>();
        ParkingSlot slot1 = new ParkingSlot();
        ParkingSlot slot2 = new ParkingSlot();
        ParkingSlot slot3 = new ParkingSlot();
        ParkingSlot slot4 = new ParkingSlot();

        slot1.setParkingNumber(1);
        slot1.setSlotReserved(true);
        slot1.setSlotBooked(false);
        slot1.setId("testID");
        slot1.setBooking(new Booking("bookTestID", "Tester1", "TC55TP1111", LocalDateTime.now(), LocalDateTime.now(), 1));
        slot1.setArrivalTime(LocalDateTime.now());

        slot2.setParkingNumber(2);
        slot2.setSlotReserved(true);
        slot2.setSlotBooked(false);
        slot2.setId("testID2");
        slot2.setBooking(new Booking("bookTestID2", "Tester2", "TC55TP2222", LocalDateTime.now(), LocalDateTime.now(), 2));
        slot2.setArrivalTime(LocalDateTime.now());

        slot3.setParkingNumber(3);
        slot3.setSlotReserved(false);
        slot3.setSlotBooked(false);
        slot3.setId("testID3");
        slot3.setBooking(new Booking("bookTestID3", "Tester3", "TC55TP3333", LocalDateTime.now(), LocalDateTime.now(), 3));
        slot3.setArrivalTime(LocalDateTime.now());

        slot4.setParkingNumber(4);
        slot4.setSlotReserved(true);
        slot4.setSlotBooked(false);
        slot4.setId("testID4");
        slot4.setBooking(new Booking("bookTestID4", "Tester4", "TC55TP4444", LocalDateTime.now(), LocalDateTime.now(), 4));
        slot4.setArrivalTime(LocalDateTime.now());

        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        when(parkingSlotRepository.getAvailableParkingSlots()).thenReturn(slotList);
        assertEquals(4, parkingService.getAvailableParkingSlots().size());
        assertTrue(parkingService.getAvailableParkingSlots().get(0).isSlotBooked()==false, "The slot is available.");
        assertTrue(parkingService.getAvailableParkingSlots().get(1).isSlotBooked()==false, "The slot is available.");
        assertTrue(parkingService.getAvailableParkingSlots().get(2).isSlotBooked()==false, "The slot is available.");
        assertTrue(parkingService.getAvailableParkingSlots().get(3).isSlotBooked()==false, "The slot is available.");
    }

    @Test
    public void getBookedParkingSlotsTest(){
        ArrayList<ParkingSlot> slotList = new ArrayList<>();
        ParkingSlot slot1 = new ParkingSlot();
        ParkingSlot slot2 = new ParkingSlot();
        ParkingSlot slot3 = new ParkingSlot();

        slot1.setParkingNumber(1);
        slot1.setSlotReserved(true);
        slot1.setSlotBooked(true);
        slot1.setId("testID");
        slot1.setBooking(new Booking("bookTestID", "Tester1", "TC55TP1111", LocalDateTime.now(), LocalDateTime.now(), 1));
        slot1.setArrivalTime(LocalDateTime.now());

        slot2.setParkingNumber(2);
        slot2.setSlotReserved(true);
        slot2.setSlotBooked(true);
        slot2.setId("testID2");
        slot2.setBooking(new Booking("bookTestID2", "Tester2", "TC55TP2222", LocalDateTime.now(), LocalDateTime.now(), 2));
        slot2.setArrivalTime(LocalDateTime.now());

        slot3.setParkingNumber(3);
        slot3.setSlotReserved(false);
        slot3.setSlotBooked(true);
        slot3.setId("testID3");
        slot3.setBooking(new Booking("bookTestID3", "Tester3", "TC55TP3333", LocalDateTime.now(), LocalDateTime.now(), 3));
        slot3.setArrivalTime(LocalDateTime.now());

        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);

        when(parkingSlotRepository.getBookedParkingSlots()).thenReturn(slotList);
        assertEquals(3, parkingService.getBookedParkingSlots().size());
        assertTrue(parkingService.getBookedParkingSlots().get(0).isSlotBooked()==true, "The slot is Booked.");
        assertTrue(parkingService.getBookedParkingSlots().get(1).isSlotBooked()==true, "The slot is Booked.");
        assertTrue(parkingService.getBookedParkingSlots().get(2).isSlotBooked()==true, "The slot is Booked.");
    }

    @Test
    public void removeBookedParkingSlotsTest(){
        List<ParkingSlot> slotList = new ArrayList<>();
        ParkingSlot slot = new ParkingSlot();

        Booking booking = new Booking(
                "bookingTestID",
                "",
                "",
                LocalDateTime.now(),
                LocalDateTime.of(2023, 1, 22, 22, 00, 00),
                1
        );

        slot.setId("testID");
        slot.setParkingNumber(1);
        slot.setSlotBooked(false);
        slot.setSlotReserved(false);
        slot.setArrivalTime(LocalDateTime.of(2023, 1, 22, 22, 00, 00));
        slot.setBooking(booking);
        slotList.add(slot);

        when(parkingService.removeBookedParkingSlots()).thenReturn(slotList);
        System.out.println(parkingService.removeBookedParkingSlots());
        assertEquals(false, parkingService.removeBookedParkingSlots().get(0).isSlotBooked());
    }
}
