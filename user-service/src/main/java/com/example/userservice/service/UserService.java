package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.*;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public GeneralSignupResponse GeneralSignup(GeneralSignupRequest request) {
        if (userRepository.existsById(request.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 사용 중인 아이디입니다.");
        }

        User user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
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
    @Transactional
    public ExpertSignupResponse ExpertSignup(ExpertSignupRequest request) {
        if (userRepository.existsById(request.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 사용 중인 아이디입니다.");
        }

        User user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .organizationName(request.getOrganizationName())
                .build();
        User savedUser = userRepository.save(user);

        return ExpertSignupResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .nickname(savedUser.getNickname())
                .role(savedUser.getRole())
                .organizationName(savedUser.getOrganizationName())
                .build();
    }
    //일반 트랜잭션에서는 JPA가 엔티티의 변경 사항을 감지하기 위해 스냅샷을 저장하고 비교
    //readOnly=true로 설정하면 이 변경 감지 메커니즘이 비활성화되어 메모리 사용량이 줄고 성능이 향상
    @Transactional(readOnly = true)
    public boolean checkDuplicateId(String id) {
        return userRepository.existsById(id);
    }

    public User findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 ID의 사용자를 찾을 수 없습니다."));
    }

}

