package com.lkznx7.nexusauth.infrastructure.security.service;

import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class SecurityDistanceVerifier {

    private static final double RAIO_TERRA_KM = 6371.0;
    private static final double VELOCIDADE_MAXIMA_HUMANA_KMPH = 250.0;
    private static final double DISTANCIA_MINIMA_KM = 50.0;

    public boolean isImpossibleTravel(
            GeoLocationService.Coordenadas antigas, LocalDateTime timestampAntigo,
            GeoLocationService.Coordenadas atuais, LocalDateTime timestampAtual) {

        if (antigas.latitude() == 0.0 && antigas.longitude() == 0.0) {
            return false;
        }

        double distancia = calcularHaversine(
                antigas.latitude(), antigas.longitude(),
                atuais.latitude(), atuais.longitude()
        );

        if (distancia < DISTANCIA_MINIMA_KM) {
            return false;
        }

        long segundos = Math.abs(Duration.between(timestampAntigo, timestampAtual).toSeconds());
        double horas = segundos / 3600.0;
        
        if (horas < 0.001) return true; 

        double velocidade = distancia / horas;
        return velocidade > VELOCIDADE_MAXIMA_HUMANA_KMPH;
    }

    private double calcularHaversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RAIO_TERRA_KM * c;
    }
}
