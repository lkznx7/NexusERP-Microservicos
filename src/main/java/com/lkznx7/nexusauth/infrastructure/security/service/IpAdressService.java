package com.lkznx7.nexusauth.infrastructure.security.service;

import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IpAdressService {

    private final String mockPublicIp;
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public IpAdressService(@Value("${app.security.mock-ip:186.232.0.1}") String mockPublicIp) {
        this.mockPublicIp = mockPublicIp;
    }

    public IPAddress extractIp(HttpServletRequest request) {
        String clientIp = "0.0.0.0";

        for (String header : HEADERS_TO_TRY) {
            String ipList = request.getHeader(header);
            if (ipList != null && !ipList.isEmpty() && !"unknown".equalsIgnoreCase(ipList)) {
                clientIp = ipList.split(",")[0].trim();
                break;
            }
        }

        if ("0.0.0.0".equals(clientIp)) {
            clientIp = request.getRemoteAddr();
        }

        if (isLocalhost(clientIp)) {
            clientIp = mockPublicIp;
        }

        return validateAndParse(clientIp);
    }

    private boolean isLocalhost(String ip) {
        return "127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip);
    }

    private IPAddress validateAndParse(String ip) {
        return Optional.ofNullable(new IPAddressString(ip).getAddress())
                .orElseThrow(() -> new IllegalArgumentException("IP inválido: " + ip));
    }
}
