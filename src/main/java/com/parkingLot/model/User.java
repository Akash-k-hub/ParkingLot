package com.parkingLot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

    @Id
    private String Id;
    private String name;
    private String password;
    private String phoneNumber;
    private String vehicleNumber;
    private boolean reservedUser;

    public User(){

    }

    public User(String id, String name, String password, String phoneNumber, String vehicleNumber, boolean reservedUser) {
        super();
        Id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.vehicleNumber = vehicleNumber;
        this.reservedUser = reservedUser;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public boolean isReservedUser() {
        return reservedUser;
    }

    public void setReservedUser(boolean reservedUser) {
        this.reservedUser = reservedUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", reservedUser=" + reservedUser +
                '}';
    }
}
