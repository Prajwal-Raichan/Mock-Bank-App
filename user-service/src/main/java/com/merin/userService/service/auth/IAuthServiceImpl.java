package com.merin.userService.service.auth;

import com.merin.userService.repository.IUserRepository;
import com.merin.userService.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IAuthServiceImpl implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }
}
