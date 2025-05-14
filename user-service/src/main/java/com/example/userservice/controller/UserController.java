package com.example.userservice.controller;

import com.example.userservice.domain.User;
import com.example.userservice.dto.ExpertSignupRequest;
import com.example.userservice.dto.ExpertSignupResponse;
import com.example.userservice.dto.GeneralSignupRequest;
import com.example.userservice.dto.GeneralSignupResponse;
//import com.example.userservice.dto.SigninRequest;
//import com.example.userservice.dto.SigninResponse;
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

    @PostMapping("/general/signup")
    public ResponseEntity<GeneralSignupResponse> GeneralSignup(@RequestBody GeneralSignupRequest request) {
        GeneralSignupResponse response = userService.GeneralSignup(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/expert/signup")
    public ResponseEntity<ExpertSignupResponse> ExpertSignup(@RequestBody ExpertSignupRequest request) {
        ExpertSignupResponse response = userService.ExpertSignup(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-id")
    public ResponseEntity<Map<String, Boolean>> checkDuplicateId(@RequestParam String id) {
        boolean isDuplicate = userService.checkDuplicateId(id);
        return ResponseEntity.ok(Map.of("isDuplicate", isDuplicate));
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }


}
