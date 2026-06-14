package com.lkznx7.nexusauth.infrastructure.persistence.entity;

import java.time.LocalDateTime;

public class AuditLog {
    private String id;
    private String action;
    private String userId;
    private LocalDateTime timestamp;
}
