package com.lkznx7.nexusauth.infrastructure.persistence.entity;

import com.lkznx7.nexusauth.domain.valueobject.UserId;
import com.lkznx7.nexusauth.infrastructure.persistence.enums.SessionStatus;

import java.time.LocalDateTime;

public class Session {
    private String id;
    private UserId userId;
    private SessionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private String ipAddress;
    private String userAgent;
}
