package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

public class SignUpDuplicatedUserEmailException extends DomainException{
    public SignUpDuplicatedUserEmailException(String field, String value) {
        super(ErrorCode.SIGNUP_DUPLICATED_USER_EMAIL, new ErrorDetail(field, value));
    }

    public SignUpDuplicatedUserEmailException() {
        super(ErrorCode.SIGNUP_DUPLICATED_USER_EMAIL, new ErrorDetail());
    }
}
