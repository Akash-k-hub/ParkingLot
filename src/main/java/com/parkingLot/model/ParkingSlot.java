package com.parkingLot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "parkingSlot")
public class ParkingSlot {
    @Id
    private String id;
    private int parkingNumber;
    private boolean slotLocked;
    private boolean slotBooked;
    private boolean slotReserved;
    private LocalDateTime arrivalTime;
    @DBRef
    private Booking booking;

    public ParkingSlot(){
    }

    public ParkingSlot(String id, int parkingNumber, boolean slotLocked, boolean slotBooked, boolean slotReserved, LocalDateTime arrivalTime, Booking booking) {
        super();
        this.id = id;
        this.parkingNumber = parkingNumber;
        this.slotLocked = slotLocked;
        this.slotBooked = slotBooked;
        this.slotReserved = slotReserved;
        this.arrivalTime = arrivalTime;
        this.booking = booking;
    }

    public int getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public boolean isSlotLocked() {
        return slotLocked;
    }

    public void setSlotLocked(boolean slotLocked) {
        this.slotLocked = slotLocked;
    }

    public boolean isSlotReserved() {
        return slotReserved;
    }

    public void setSlotReserved(boolean slotReserved) {
        this.slotReserved = slotReserved;
    }


    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isSlotBooked() {
        return slotBooked;
    }

    public void setSlotBooked(boolean slotBooked) {
        this.slotBooked = slotBooked;
    }

    public Booking getBooking() {
        return booking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id='" + id + '\'' +
                ", parkingNumber=" + parkingNumber +
                ", slotLocked=" + slotLocked +
                ", slotBooked=" + slotBooked +
                ", slotReserved=" + slotReserved +
                ", arrivalTime=" + arrivalTime +
                ", booking=" + booking +
                '}';
    }
}
