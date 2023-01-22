package com.parkingLot.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.parkingLot.model.User;
import com.parkingLot.repository.UserRepository;
import com.parkingLot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void registerUserTest(){
        User user = new User("666", "testUser", "pswd123", "9876543210", "UP54FB6587", false);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.registerUser(user));
    }

    @Test
    public void getAllUsersTest(){
        List<User> userList = new ArrayList<>();

        User user1 = new User("666", "testUser1", "pswd123", "9876543210", "UP54FB6587", false);
        User user2 = new User("010", "testUser2", "pswd2@1", "9876543210", "UP32AS8055", true);
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    public void getUserByVehicleNumberTest(){
        String vehicleNumber = "UP32FB8185";
        when(userRepository.validateVehicleNumber(vehicleNumber)).thenReturn(new User(
                "000", "testUser", "pswd@123", "9874563210", "UP32FB8185", true));
        assertEquals("testUser", userService.getUserByVehicleNumber(vehicleNumber).getName());
    }
}
