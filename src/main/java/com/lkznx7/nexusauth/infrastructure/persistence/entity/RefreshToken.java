package com.lkznx7.nexusauth.infrastructure.persistence.entity;

import com.lkznx7.nexusauth.domain.valueobject.UserId;

import java.time.LocalDateTime;

public class RefreshToken {
    private String token;
    private UserId userId;
    private LocalDateTime expiresAt;
    private boolean revoked;
}
