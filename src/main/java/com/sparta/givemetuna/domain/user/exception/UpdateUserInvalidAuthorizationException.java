package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 회원의 수정 권한이 없습니다."
public class UpdateUserInvalidAuthorizationException extends DomainException {

	public UpdateUserInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.UPDATE_USER_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public UpdateUserInvalidAuthorizationException() {
		super(ErrorCode.UPDATE_USER_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}