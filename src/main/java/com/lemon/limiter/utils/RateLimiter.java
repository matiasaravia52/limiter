package com.lemon.limiter.utils;

public class RateLimiter {

    private static final int MAX_REQUESTS = 5;
    private int countRequest;

    public RateLimiter() {
        countRequest = 1;
    }

    public synchronized boolean canAdd() {
        if (countRequest <= MAX_REQUESTS) {
            countRequest++;
            return true;
        } else {
            return false;
        }
    }
}
