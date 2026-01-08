package com.vromanyu.apigateway.controller;

import com.vromanyu.apigateway.dto.ServiceUnavailableResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class FallbackController {

    private static final Logger logger = LoggerFactory.getLogger(FallbackController.class);


    @RequestMapping("/unavailable")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Mono<ServiceUnavailableResponseDto> unavailable() {
        logger.info("{} executed", this.getClass().getName());
        return Mono.just(new ServiceUnavailableResponseDto(
                "service is temporary unavailable",
                LocalDateTime.now()
        ));
    }
}
