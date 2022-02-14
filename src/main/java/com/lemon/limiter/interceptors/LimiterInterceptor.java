package com.lemon.limiter.interceptors;

import com.google.common.base.Strings;
import com.lemon.limiter.cache.LocalCacheStore;
import com.lemon.limiter.utils.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LimiterInterceptor implements HandlerInterceptor {

    private static final String HEADER_USER_ID = "user-id";

    private LocalCacheStore localCacheStore;

    public LimiterInterceptor(LocalCacheStore localCacheStore) {
        this.localCacheStore = localCacheStore;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader(HEADER_USER_ID);

        if (Strings.isNullOrEmpty(userId)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }

        RateLimiter rateLimiter = localCacheStore.get(userId);

        if (rateLimiter == null) {
            rateLimiter = new RateLimiter();
            localCacheStore.add(userId, rateLimiter);
        }

        if (rateLimiter.canAdd()) {
            response.setStatus(HttpStatus.OK.value());
            return true;
        }

        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        return false;
    }
}
