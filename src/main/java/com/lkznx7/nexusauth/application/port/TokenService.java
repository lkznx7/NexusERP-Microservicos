package com.lkznx7.nexusauth.application.port;

import com.lkznx7.nexusauth.domain.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TokenService {

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    boolean validateToken(String token);

    String getSubject(String token);

    Map<String, Object> getClaims(String token);

    List<String> getAuthorities(String token);

    LocalDateTime getExpiration(String token);

    void revokeToken(String token);
}
