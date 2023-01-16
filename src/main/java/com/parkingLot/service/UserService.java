package com.parkingLot.service;

import com.parkingLot.model.User;
import com.parkingLot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    registering the user
    public User registerUser(User user){
        return userRepository.save(user);
    }

//    find the list of users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

//    return the totalCount of users
    public int userCount(){
        return userRepository.findAll().size();
    }

    public User getUserByVehicleNumber(String vehicleNumber){
        return userRepository.validateVehicleNumber(vehicleNumber);
    }
}
