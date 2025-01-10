package com.merin.userService.controller;

import com.merin.userService.dto.UserResponse;
import com.merin.userService.entity.User;
import com.merin.userService.service.user.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User-Service Controller",description = "User-Service Management Portal")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody User user) {
        log.info("Adding new user");
        UserResponse userResponse = new UserResponse();
        userResponse = userService.registerUser(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserResponse> fetchUserById(@RequestParam int userId) {

        UserResponse userResponse = new UserResponse();
        userResponse = userService.fetchUserById(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponse>> fetchAllUsers() {
        List<UserResponse> usersResponseList = userService.fetchAllUsers();
        return new ResponseEntity<>(usersResponseList, HttpStatus.OK);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateUserOtp(@RequestParam String email, @RequestParam String otp) {

        boolean isValid = userService.validateOtp(email, otp);
        if (isValid) {
            return ResponseEntity.ok("OTP validated successfully.");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP.");
        }
    }


}
