package com.example.authservice.dto;

import com.example.authservice.domain.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SigninResponse {
    private String id;
    private UserRole role;
    private String accessToken;
    private String refreshToken;
}
