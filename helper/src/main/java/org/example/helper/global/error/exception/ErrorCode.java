package org.example.helper.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // general
    BAD_REQUEST(400, "잘못된 요청입니다"),
    INTERNAL_SERVER_ERROR(500, "server error");

    private final int statusCode;
    private final String statusMessage;
}
