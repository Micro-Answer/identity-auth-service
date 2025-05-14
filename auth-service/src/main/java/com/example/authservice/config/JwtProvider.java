package com.example.authservice.config;

import com.example.authservice.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    @Value("${jwt.refreshExpiration}")
    private long refreshExpirationMs;

    // AccessToken 생성
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId())  // JWT의 Subject
                .claim("role", user.getRole().toString())  // 사용자 역할 (권한)
                .setIssuedAt(new Date())  // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))  // 만료 시간
                .signWith(SignatureAlgorithm.HS512, jwtSecret)  // 서명 (비밀 키 사용)
                .compact();
    }

    // RefreshToken 생성
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId())  // JWT의 Subject
                .setIssuedAt(new Date())  // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationMs))  // 만료 시간
                .signWith(SignatureAlgorithm.HS512, jwtSecret)  // 서명
                .compact();
    }

    // 토큰에서 사용자 이름 가져오기 (토큰을 파싱하여)
    public String getIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;  // 유효한 토큰
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT claims string is empty.");
        }
    }
}
