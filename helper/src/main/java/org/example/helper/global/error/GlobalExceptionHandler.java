package org.example.helper.global.error;

import org.example.helper.global.error.exception.BusinessException;
import org.example.helper.global.error.exception.ErrorCode;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> exceptionHandler (BusinessException e) {
        ErrorCode errorCode = e.errorCode;
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getStatusMessage());

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(errorCode.getStatusCode()));
    }
}
