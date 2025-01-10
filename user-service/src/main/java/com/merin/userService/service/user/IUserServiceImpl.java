package com.merin.userService.service.user;

import com.merin.userService.dto.UserResponse;
import com.merin.userService.entity.Customer;
import com.merin.userService.entity.User;
import com.merin.userService.mapper.UserMapper;
import com.merin.userService.repository.ICustomerRepository;
import com.merin.userService.repository.IUserRepository;
import com.merin.userService.util.jwt.OtpServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OtpServiceUtil otpServiceUtil;


    @Override
    public boolean validateOtp(String email, String providedOtp) {
        Customer customer = customerRepository.getByEmailIdLike(email);
        if (customer != null && customer.getTempOtp().equals(providedOtp)) {
            customer.setTempOtp(null);
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    @Override
    public UserResponse registerUser(User user) {

        User userEntity = new User();
        UserResponse userResponse = new UserResponse();

        String otp = otpServiceUtil.generateOtp();
        String customerEmail = user.getCustomer().getEmailId();
        otpServiceUtil.sendOtpEmail(customerEmail, otp);
        user.getCustomer().setTempOtp(otp);

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userEntity = userRepository.save(user);
        userResponse = userMapper.mapUserToResponseDto(userEntity);
        userResponse.setResponseMessage("User Registered Successfully");
        return userResponse;
    }

    @Override
    public UserResponse fetchUserById(int userId) {

        User userEntity = new User();
        UserResponse userResponse = new UserResponse();
        userEntity = userRepository.findById(userId).get();
        userResponse = userMapper.mapUserToResponseDto(userEntity);
        userResponse.setResponseMessage("User Details Fetched Successfully");
        return userResponse;
    }

    @Override
    public List<UserResponse> fetchAllUsers() {

        List<User> userEntity = new ArrayList<>();
        List<UserResponse> userResponseList = new ArrayList<>();
        userEntity = userRepository.findAll();
        userResponseList = userMapper.mapUserListToResponseDtoList(userEntity);
        return userResponseList;
    }
}
