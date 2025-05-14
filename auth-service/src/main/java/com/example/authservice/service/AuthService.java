package com.example.authservice.service;

import com.example.authservice.Exception.InvalidCredentialsException;
import com.example.authservice.client.UserClient;
import com.example.authservice.config.JwtProvider;
import com.example.authservice.domain.User;
import com.example.authservice.dto.SigninResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserClient userClient;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public SigninResponse signin(String id, String password){
        User user = userClient.getUserByUserId(id);

        if(!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("no");
            throw new InvalidCredentialsException();
        }
        String accessToken = jwtProvider.generateAccessToken(user);
        String refreshToken = jwtProvider.generateRefreshToken(user);

        System.out.println("refreshToken = " + refreshToken);
        System.out.println("accessToken = " + accessToken);
                return SigninResponse.builder()
                .id(user.getId())
                .role(user.getRole())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

