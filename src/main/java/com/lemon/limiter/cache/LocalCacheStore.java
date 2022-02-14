package com.lemon.limiter.cache;

import com.lemon.limiter.utils.RateLimiter;

import java.util.concurrent.TimeUnit;

public class LocalCacheStore extends CacheStore<RateLimiter>{

    public LocalCacheStore() {
        super(10, TimeUnit.SECONDS);
    }
}
