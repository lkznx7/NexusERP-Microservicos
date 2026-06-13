package com.lkznx7.nexusauth.domain.valueobject;

public record Email(String value) {
    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
    }
}
