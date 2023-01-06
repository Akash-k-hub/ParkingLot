package com.parkingLot.model;

import org.springframework.data.annotation.Id;

public class ParkingSlot {
    @Id
    private String id;

    private int parkingNumber;

    private boolean slotAvailableOrOccupied;

    private boolean slotReserved;

    private String booking;

    private String arrivalTime;

    public int getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public boolean isSlotAvailableOrOccupied() {
        return slotAvailableOrOccupied;
    }

    public void setSlotAvailableOrOccupied(boolean slotAvailableOrOccupied) {
        this.slotAvailableOrOccupied = slotAvailableOrOccupied;
    }

    public boolean isSlotReserved() {
        return slotReserved;
    }

    public void setSlotReserved(boolean slotReserved) {
        this.slotReserved = slotReserved;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
