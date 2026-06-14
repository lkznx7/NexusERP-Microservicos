package com.lkznx7.nexusauth.infrastructure.security.jwt;

import com.lkznx7.nexusauth.application.port.TokenService;
import com.lkznx7.nexusauth.infrastructure.persistence.entity.User;
import com.lkznx7.nexusauth.infrastructure.persistence.repository.JpaUserRepository;
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
public class JwtTokenService {

    private final JwtProperties jwtProperties;
    private final JpaUserRepository jpaUserRepository;

    public JwtTokenService(JwtProperties jwtProperties, JpaUserRepository jpaUserRepository) {
        this.jwtProperties = jwtProperties;
        this.jpaUserRepository = jpaUserRepository;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getExpiration());

        List<String> roles = user.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
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

    public String generateRefreshToken(User user) {
        Date expirationRefreshToken = new Date(System.currentTimeMillis() + jwtProperties.getExpiration());
        return Jwts.builder().subject(user.getUsername())
                .id(UUID.randomUUID().toString())
                .expiration(expirationRefreshToken)
                .issuedAt(new Date())
                .signWith(getSigningKey()).compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getAuthorities(String token) {
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get("roles");
    }

    public Map<String, Object> getClaims(String token) {
        return extractAllClaims(token);
    }

    public LocalDateTime getExpiration(String token) {
        Date expirationDate = extractAllClaims(token).getExpiration();
        return expirationDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void revokeToken(String token) {
        var user = jpaUserRepository.findByUsername(token);
        Claims claims = extractAllClaims(token);
        // token expirado,dois token mesmo usuario,login em ip diferente e aparelho, mudar a senha , user.status=locked, token reutilizado

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
