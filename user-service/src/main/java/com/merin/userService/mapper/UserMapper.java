package com.merin.userService.mapper;

import com.merin.userService.dto.UserResponse;
import com.merin.userService.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse mapUserToResponseDto(User user) {

        return UserResponse.builder()
                .username(user.getUserName())
                .build();
    }

    public List<UserResponse> mapUserListToResponseDtoList(List<User> userEntity) {

        return userEntity.stream()
                .map(this::mapUserToResponseDto)
                .collect(Collectors.toList());
    }
}
