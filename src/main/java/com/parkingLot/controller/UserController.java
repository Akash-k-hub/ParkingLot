package com.parkingLot.controller;

import com.parkingLot.model.User;
import com.parkingLot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        userService.registerUser(user);
        return "User Registered!";
    }

    @GetMapping("/getAllUsers")
    public List<User> findAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/countUser")
    public String countUser(){
        int userCount = userService.userCount();
        return "Total Registered User : " + userCount;
    }
}
