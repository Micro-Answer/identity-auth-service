package com.example.authservice.client;

import com.example.authservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    @Value("${user-service.url}")
    private String userServiceUrl;

    public User getUserByUserId(String userId) {
        String url = userServiceUrl + "/v1/api/users/id/" + userId;
        System.out.println(" 요청 성공 + userId = " + userId);
        try {
            ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new UsernameNotFoundException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
    }
}