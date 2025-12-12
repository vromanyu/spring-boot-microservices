package com.vromanyu.apigateway.controller;

import com.vromanyu.apigateway.dto.ServiceUnavailableResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class FallbackController {


    @RequestMapping("/unavailable")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Mono<ServiceUnavailableResponseDto> unavailable() {
        return Mono.just(new ServiceUnavailableResponseDto(
                "service is temporary unavailable",
                LocalDateTime.now()
        ));
    }
}
