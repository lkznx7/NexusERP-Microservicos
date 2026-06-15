package com.lkznx7.nexusauth.application.port;

public interface CacheService {
    void put(String key, String value, long expirationInSeconds);
    String get(String key);
    void remove(String key);
}
