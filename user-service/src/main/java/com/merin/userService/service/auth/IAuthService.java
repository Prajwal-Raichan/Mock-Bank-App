package com.merin.userService.service.auth;



public interface IAuthService {


    String generateToken(String username);

    void validateToken(String token);
}
