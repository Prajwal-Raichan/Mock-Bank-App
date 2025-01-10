package com.merin.userService.service.user;

import com.merin.userService.dto.UserResponse;
import com.merin.userService.entity.User;

import java.util.List;

public interface IUserService {

    UserResponse registerUser(User user);

    UserResponse fetchUserById(int userId);

    List<UserResponse> fetchAllUsers();

    boolean validateOtp(String email, String otp);
}
