package com.vromanyu.employeems.dto;

import java.time.LocalDateTime;

public record InternalErrorDto(
        String path,
        String error,
        Integer code,
        LocalDateTime timestamp
) {
}
