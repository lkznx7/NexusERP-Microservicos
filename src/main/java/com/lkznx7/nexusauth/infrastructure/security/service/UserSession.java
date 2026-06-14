package com.lkznx7.nexusauth.infrastructure.security.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_sessions")
public class UserSession {

    @Id
    @Column(length = 500)
    private String refreshToken;

    @Column(nullable = false)
    private String deviceIdentifier;

    private double lastLatitude;
    private double lastLongitude;

    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    public UserSession() {}

    public UserSession(String refreshToken, String deviceIdentifier, double lastLatitude, 
                       double lastLongitude, LocalDateTime lastUpdatedAt, LocalDateTime expiresAt) {
        this.refreshToken = refreshToken;
        this.deviceIdentifier = deviceIdentifier;
        this.lastLatitude = lastLatitude;
        this.lastLongitude = lastLongitude;
        this.lastUpdatedAt = lastUpdatedAt;
        this.expiresAt = expiresAt;
    }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public String getDeviceIdentifier() { return deviceIdentifier; }
    public void setDeviceIdentifier(String deviceIdentifier) { this.deviceIdentifier = deviceIdentifier; }

    public double getLastLatitude() { return lastLatitude; }
    public void setLastLatitude(double lastLatitude) { this.lastLatitude = lastLatitude; }

    public double getLastLongitude() { return lastLongitude; }
    public void setLastLongitude(double lastLongitude) { this.lastLongitude = lastLongitude; }

    public LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }
    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession that = (UserSession) o;
        return Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refreshToken);
    }
}
