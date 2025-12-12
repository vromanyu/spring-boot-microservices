package com.vromanyu.apigateway.dto;

import java.time.LocalDateTime;

public record ServiceUnavailableResponseDto(String message, LocalDateTime timestamp) {
}
