package com.example.userservice.dto;

import com.example.userservice.domain.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpertSignupRequest {

    private String id;
    private String name;
    private String nickname;
    private String password;
    private UserRole role;
    private String organizationName;

}
