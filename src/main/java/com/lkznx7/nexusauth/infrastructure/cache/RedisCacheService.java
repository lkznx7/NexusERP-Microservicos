package com.lkznx7.nexusauth.infrastructure.cache;

import com.lkznx7.nexusauth.application.port.CacheService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheService implements CacheService {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisCacheService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void put(String key, String value, long expirationInSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, expirationInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }
}
