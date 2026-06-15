package com.lkznx7.nexusauth.domain.event;

public class UserRegistered {
    private final String userId;
    private final String email;

    public UserRegistered(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
