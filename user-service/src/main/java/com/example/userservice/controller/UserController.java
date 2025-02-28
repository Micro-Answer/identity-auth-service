package com.example.userservice.controller;

import com.example.userservice.dto.GeneralSignupRequest;
import com.example.userservice.dto.GeneralSignupResponse;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<GeneralSignupResponse> signup(@RequestBody GeneralSignupRequest request) {
        GeneralSignupResponse response = userService.signup(request);
        return ResponseEntity.ok(response);
    }
}
