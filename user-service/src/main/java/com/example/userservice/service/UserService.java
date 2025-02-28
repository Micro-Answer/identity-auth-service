package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.GeneralSignupRequest;
import com.example.userservice.dto.GeneralSignupResponse;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public GeneralSignupResponse signup(GeneralSignupRequest request) {
        User user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        User savedUser = userRepository.save(user);

        return GeneralSignupResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .nickname(savedUser.getNickname())
                .role(savedUser.getRole())
                .build();
    }
}

