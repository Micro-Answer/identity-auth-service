package com.example.userservice.controller;

import com.example.userservice.dto.GeneralSignupRequest;
import com.example.userservice.dto.GeneralSignupResponse;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<GeneralSignupResponse> signup(@RequestBody GeneralSignupRequest request) {
        GeneralSignupResponse response = userService.signup(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-id")
    public ResponseEntity<Map<String, Boolean>> checkDuplicateId(@RequestParam String id) {
        boolean isDuplicate = userService.checkDuplicateId(id);
        return ResponseEntity.ok(Map.of("isDuplicate", isDuplicate));
    }
}
