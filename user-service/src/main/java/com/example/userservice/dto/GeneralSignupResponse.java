package com.example.userservice.dto;

import com.example.userservice.domain.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeneralSignupResponse {
    private String id;
    private String name;
    private String nickname;
    private UserRole role;
}
