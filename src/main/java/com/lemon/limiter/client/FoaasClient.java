package com.lemon.limiter.client;

import com.lemon.limiter.client.dto.MessageResponse;
import com.lemon.limiter.config.FeignConfig;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "FoaasClient", url = "https://www.foaas.com", configuration = FeignConfig.class)
public interface FoaasClient {

    @RequestLine("GET /cool/test")
    @Headers(value = "Accept:application/json")
    MessageResponse getMessage();

}
