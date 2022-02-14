package com.lemon.limiter.service;

import com.lemon.limiter.client.FoaasClient;
import com.lemon.limiter.client.dto.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    private FoaasClient foaasClient;

    public ChallengeService(FoaasClient foaasClient) {
        this.foaasClient = foaasClient;
    }

    public MessageResponse getMessage() {
        return foaasClient.getMessage();
    }
}
