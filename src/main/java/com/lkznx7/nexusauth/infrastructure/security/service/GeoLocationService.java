package com.lkznx7.nexusauth.infrastructure.security.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import inet.ipaddr.IPAddress;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

@Service
public class GeoLocationService {

    private final Resource databaseResource;
    private DatabaseReader databaseReader;

    public record Coordenadas(double latitude, double longitude) {}

    public GeoLocationService(@Value("classpath:geo/GeoLite2-City.mmdb") Resource databaseResource) {
        this.databaseResource = databaseResource;
    }

    @PostConstruct
    public void init() throws IOException {
        try (InputStream inputStream = databaseResource.getInputStream()) {
            this.databaseReader = new DatabaseReader.Builder(inputStream).build();
        }
    }

    public Coordenadas getGeoLocation(IPAddress ipAddress) {
        try {
            InetAddress inetAddress = ipAddress.toInetAddress();
            CityResponse response = databaseReader.city(inetAddress);
            
            if (response.getLocation() != null) {
                return new Coordenadas(
                        response.getLocation().getLatitude(),
                        response.getLocation().getLongitude()
                );
            }
        } catch (Exception e) {
            // Log silenciado conforme restrição de limpeza
        }
        return new Coordenadas(0.0, 0.0);
    }
}
