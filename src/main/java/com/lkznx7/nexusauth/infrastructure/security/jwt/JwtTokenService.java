package com.lkznx7.nexusauth.infrastructure.security.jwt;

import com.lkznx7.nexusauth.application.port.CacheService;
import com.lkznx7.nexusauth.application.port.TokenService;
import com.lkznx7.nexusauth.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JwtTokenService implements TokenService {

    private final JwtProperties jwtProperties;
    private final CacheService cacheService;

    public JwtTokenService(JwtProperties jwtProperties, CacheService cacheService) {
        this.jwtProperties = jwtProperties;
        this.cacheService = cacheService;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateAccessToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getExpiration());

        List<String> roles = user.getRoles().stream()
                .map(role -> "ROLE_" + role.getName())
                .collect(Collectors.toList());

        return Jwts.builder()
                .subject(user.getEmail().value())
                .claim("roles", roles)
                .claim("username", user.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 604800000); // 7 days default if not in properties

        return Jwts.builder()
                .subject(user.getEmail().value())
                .id(UUID.randomUUID().toString())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            if (cacheService.get("blacklist:" + token) != null) {
                return false;
            }
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getAuthorities(String token) {
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get("roles");
    }

    @Override
    public Map<String, Object> getClaims(String token) {
        return extractAllClaims(token);
    }

    @Override
    public LocalDateTime getExpiration(String token) {
        Date expirationDate = extractAllClaims(token).getExpiration();
        return expirationDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Override
    public void revokeToken(String token) {
        long expirationTimeInSeconds = getExpiration(token).atZone(ZoneId.systemDefault()).toEpochSecond() - LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        if (expirationTimeInSeconds > 0) {
            cacheService.put("blacklist:" + token, "revoked", expirationTimeInSeconds);
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
