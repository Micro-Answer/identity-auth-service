package com.example.userservice.dto;

import com.example.userservice.domain.UserRole;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExpertSignupResponse {
    private String id;
    private String name;
    private String nickname;
    private UserRole role;
    private String organizationName;
}
