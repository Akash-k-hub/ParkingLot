package com.parkingLot.controller;

import com.parkingLot.model.User;
import com.parkingLot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        log.info("service=UserController; method=registerUser; message=user {} registered", user.getName());
        userService.registerUser(user);
        return "User Registered!";
    }

    @GetMapping("/getAllUsers")
    public List<User> findAllUsers(){
        log.info("service=UserController; method=findAllUsers; message=list of all users");
        return userService.getAllUsers();
    }

    @GetMapping("/countUser")
    public String countUser(){
        log.info("service=UserController; method=countUser; message=count of all users");
        int userCount = userService.userCount();
        return "Total Registered User : " + userCount;
    }
}
