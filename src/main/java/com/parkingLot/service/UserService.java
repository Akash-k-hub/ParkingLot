package com.parkingLot.service;

import com.parkingLot.model.User;
import com.parkingLot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    registering the user
    public User registerUser(User user){
        log.info("service=UserService; method=registerUser");
        return userRepository.save(user);
    }

//    find the list of users
    public List<User> getAllUsers(){
        log.info("service=UserService; method=getAllUsers; message=list of all users");
        List<User> userList = new ArrayList<>();

        List<User> allUserList = new ArrayList<>();
        userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++){
            User user = new User();
            user.setName(userList.get(i).getName());
            user.setVehicleNumber(userList.get(i).getVehicleNumber());
            user.setReservedUser(userList.get(i).isReservedUser());

            allUserList.add(user);
        }
        return allUserList;
    }

//    return the totalCount of users
    public int userCount(){
        log.info("service=UserService; method=userCount");
        return userRepository.findAll().size();
    }

    public User getUserByVehicleNumber(String vehicleNumber){
        log.info("service=UserService; method=getUserByVehicleNumber; message=retrieve user by vehicleNumber {}", vehicleNumber);
        return userRepository.validateVehicleNumber(vehicleNumber);
    }
}
