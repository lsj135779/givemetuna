package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 회원의 수정 권한이 없습니다."
public class DeleteUserInvalidAuthorizationException extends DomainException {

	public DeleteUserInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.DELETE_USER_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public DeleteUserInvalidAuthorizationException() {
		super(ErrorCode.DELETE_USER_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}