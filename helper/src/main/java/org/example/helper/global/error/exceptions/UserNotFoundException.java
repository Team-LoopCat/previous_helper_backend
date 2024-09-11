package org.example.helper.global.error.exceptions;

import org.example.helper.global.error.exception.BusinessException;
import org.example.helper.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();
    public UserNotFoundException() { super(ErrorCode.USER_NOT_FOUND); }
}
