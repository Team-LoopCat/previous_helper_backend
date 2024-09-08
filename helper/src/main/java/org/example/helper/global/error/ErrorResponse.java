package org.example.helper.global.error;

import lombok.RequiredArgsConstructor;
import org.example.helper.global.error.exception.ErrorCode;

import java.time.LocalDateTime;

public record ErrorResponse (
        int statusCode,
        String statusMessage,
        String description,
        LocalDateTime timeStamp
) {
    public static ErrorResponse of(ErrorCode errorCode, String description) {
        return new ErrorResponse(errorCode.getStatusCode(), errorCode.getStatusMessage(), description, LocalDateTime.now());
    }
}
