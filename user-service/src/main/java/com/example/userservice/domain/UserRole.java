package com.example.userservice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    GENERAL("GENERAL"),
    MANAGER("MANAGER"),
    EXPERT("EXPERT");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @JsonValue // JSON 직렬화 시 사용됨
    public String getValue() {
        return value;
    }

    @JsonCreator // JSON -> Enum 변환 시 사용됨
    public static UserRole fromValue(String value) {
        if (value == null) {
            return null;
        }

        for (UserRole role : UserRole.values()) {
            if (role.value.equalsIgnoreCase(value)) { // 대소문자 무시
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}