package com.lkznx7.nexusauth.domain.valueobject;

import java.util.UUID;

public record UserId(UUID value) {
    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }
    }
    
    public static UserId random() {
        return new UserId(UUID.randomUUID());
    }
}
