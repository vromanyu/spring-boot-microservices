package com.vromanyu.departmentms.dto;

import java.time.LocalDateTime;

public record InternalErrorDto(
        String path,
        String error,
        Integer code,
        LocalDateTime timestamp
) {
}
