package org.example.helper.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // jwt
    USER_NOT_FOUND(404, "유저를 찾을 수 없습니다"),
    UNKNOWN_ROLE(404, "존재하지 않는 역할이 JWT에 저장되어 있습니다"),

    // general
    BAD_REQUEST(400, "잘못된 요청입니다"),
    INTERNAL_SERVER_ERROR(500, "server error");

    private final int statusCode;
    private final String statusMessage;
}
