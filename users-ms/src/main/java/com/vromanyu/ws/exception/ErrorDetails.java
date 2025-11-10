package com.vromanyu.ws.exception;

import java.time.OffsetDateTime;

public record ErrorDetails(
        OffsetDateTime timestamp,
        String message,
        String path,
        String errorCode
) {
}
