package com.parkingLot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "booking")
public class Booking {
    @Id
    private String id;

    private String userName;

    private String vehicleNumber;

    private LocalDateTime bookingTime;

    private LocalDateTime arrivalTime;

    private int parkingNumber;

    public Booking(){

    }

    public Booking(String id, String userName, String vehicleNumber, LocalDateTime bookingTime, LocalDateTime arrivalTime, int parkingNumber) {
        super();
        this.id = id;
        this.userName = userName;
        this.vehicleNumber = vehicleNumber;
        this.bookingTime = bookingTime;
        this.arrivalTime = arrivalTime;
        this.parkingNumber = parkingNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", bookingTime=" + bookingTime +
                ", arrivalTime=" + arrivalTime +
                ", parkingNumber=" + parkingNumber +
                '}';
    }
}
