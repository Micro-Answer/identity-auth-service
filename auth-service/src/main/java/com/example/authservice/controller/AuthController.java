package com.example.authservice.controller;

import com.example.authservice.dto.SigninRequest;
import com.example.authservice.dto.SigninResponse;
import com.example.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request){
        SigninResponse tokens = authService.signin(request.getId(), request.getPassword());
        return ResponseEntity.ok(tokens);
    }
}
