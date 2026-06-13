package com.lkznx7.nexusauth.domain.valueobject;

public record Password(String value) {
    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }
}
