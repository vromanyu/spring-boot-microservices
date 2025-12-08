package com.vromanyu.employeems.dto;

import java.time.LocalDateTime;

public record BadRequestDto(
        String path,
        String error,
        Integer code,
        LocalDateTime timestamp
) {
}
