package com.parkingLot.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Parking {

    @Id
    private String id;

    private int totalParkingCapacity;

    @DBRef
    private List<ParkingSlot> parkingSlot;

    public int getTotalParkingCapacity() {
        return totalParkingCapacity;
    }

    public void setTotalParkingCapacity(int totalParkingCapacity) {
        this.totalParkingCapacity = totalParkingCapacity;
    }

    public List<ParkingSlot> getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(List<ParkingSlot> parkingSlot) {
        this.parkingSlot = parkingSlot;
    }
}
