package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "올바르지 않은 아이디로 로그인을 시도하셨습니다."
public class LoginInvalidAccountException extends DomainException {

	public LoginInvalidAccountException(String field, String value) {
		super(ErrorCode.LOGIN_INVALID_ACCOUNT, new ErrorDetail(field, value));
	}

	public LoginInvalidAccountException() {
		super(ErrorCode.LOGIN_INVALID_ACCOUNT, new ErrorDetail());
	}
}