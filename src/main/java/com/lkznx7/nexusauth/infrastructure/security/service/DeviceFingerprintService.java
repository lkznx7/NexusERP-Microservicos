package com.lkznx7.nexusauth.infrastructure.security.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceFingerprintService {

    private static final String COOKIE_NAME = "DEVICE_FINGERPRINT";
    private static final int ONE_YEAR_SECONDS = 365 * 24 * 60 * 60;

    public String gerarEInjetarCookie(HttpServletResponse response) {
        String deviceId = UUID.randomUUID().toString();
        
        Cookie cookie = new Cookie(COOKIE_NAME, deviceId);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(ONE_YEAR_SECONDS);
        
        response.addCookie(cookie);
        return deviceId;
    }

    public String extrairCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if (COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
