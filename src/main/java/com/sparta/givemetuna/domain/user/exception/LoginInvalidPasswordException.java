package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "올바르지 않은 비밀번호로 로그인을 시도하셨습니다."
public class LoginInvalidPasswordException extends DomainException {

	public LoginInvalidPasswordException(String field, String value) {
		super(ErrorCode.LOGIN_INVALID_PASSWORD, new ErrorDetail(field, value));
	}

	public LoginInvalidPasswordException() {
		super(ErrorCode.LOGIN_INVALID_PASSWORD, new ErrorDetail());
	}
}