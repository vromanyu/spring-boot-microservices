package com.vromanyu.apigateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationInfoController {

    private static final Logger logger =  LoggerFactory.getLogger(AuthenticationInfoController.class);

    @GetMapping("/auth-info")
    public Mono<Void> authenticationInformation(@AuthenticationPrincipal JwtAuthenticationToken jwtAuthenticationToken) {
        jwtAuthenticationToken.getAuthorities().forEach(authority -> logger.info("Authority: {}", authority));
        return Mono.empty();
    }
}
