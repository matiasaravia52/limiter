package com.lemon.limiter.interceptors;

import com.lemon.limiter.cache.LocalCacheStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class LimiterInterceptorTest {

    @InjectMocks
    LimiterInterceptor limiterInterceptor;

    @Spy
    LocalCacheStore localCacheStore;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void test_pre_handle_with_five_requests() throws Exception {
        String userId = "123-test";

        when(localCacheStore.get(userId)).thenCallRealMethod();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("user-id", userId);

        MockHttpServletResponse response = new MockHttpServletResponse();

        for (int i = 0; i < 5; i++) {
            boolean isOk = limiterInterceptor.preHandle(request, response, new Object());
            assertThat(isOk).isTrue();
            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        }
    }

    @Test
    void test_pre_handle_with_six_requests() throws Exception {
        String userId = "123-test";

        when(localCacheStore.get(userId)).thenCallRealMethod();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("user-id", userId);

        MockHttpServletResponse response = new MockHttpServletResponse();

        for (int i = 0; i < 6; i++) {
            boolean isOk = limiterInterceptor.preHandle(request, response, new Object());
            if (i < 5) {
                assertThat(isOk).isTrue();
                assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            } else {
                assertThat(isOk).isFalse();
                assertThat(response.getStatus()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS.value());
            }
        }
    }

    @Test
    void test_pre_handle_with_seven_requests() throws Exception {
        String userId = "123-test";

        when(localCacheStore.get(userId)).thenCallRealMethod();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("user-id", userId);

        MockHttpServletResponse response = new MockHttpServletResponse();

        for (int i = 0; i < 6; i++) {
            boolean isOk = limiterInterceptor.preHandle(request, response, new Object());
            if (i < 5) {
                assertThat(isOk).isTrue();
                assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            } else {
                assertThat(isOk).isFalse();
                assertThat(response.getStatus()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS.value());
            }
        }

        Thread.sleep(10000);
        boolean isOk = limiterInterceptor.preHandle(request, response, new Object());
        assertThat(isOk).isTrue();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}