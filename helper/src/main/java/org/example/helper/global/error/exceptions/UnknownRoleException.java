package org.example.helper.global.error.exceptions;

import org.example.helper.global.error.exception.BusinessException;
import org.example.helper.global.error.exception.ErrorCode;

public class UnknownRoleException extends BusinessException {

    public static final UnknownRoleException EXCEPTION = new UnknownRoleException();
    public UnknownRoleException() { super(ErrorCode.UNKNOWN_ROLE); }
}
