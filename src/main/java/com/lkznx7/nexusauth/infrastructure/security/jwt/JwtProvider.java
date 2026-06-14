package com.lkznx7.nexusauth.infrastructure.security.jwt;

import org.springframework.stereotype.Component;
import java.security.Key;

@Component
public class JwtProvider {

    public String createToken() {
        return null;
    }

    public boolean isTokenValid(String token) {
        return false;
    }

    private Key getSigningKey() {
        // Lógica para gerar/recuperar a chave de assinatura
        return null;
    }
}
