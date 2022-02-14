package com.lemon.limiter.controller;

import com.lemon.limiter.client.dto.MessageResponse;
import com.lemon.limiter.service.ChallengeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/message")
    public ResponseEntity<MessageResponse> getMessage() {
        return ResponseEntity.ok(challengeService.getMessage());
    }
}
