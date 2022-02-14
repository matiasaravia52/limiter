package com.lemon.limiter.config;

import com.lemon.limiter.cache.LocalCacheStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheStoreBeans {

    @Bean
    public LocalCacheStore rateLimiterCache() {
        return new LocalCacheStore();
    }
}
