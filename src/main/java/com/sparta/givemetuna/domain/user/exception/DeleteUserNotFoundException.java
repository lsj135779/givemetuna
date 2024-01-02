package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "삭제하시려는 회원은 존재하지 않습니다."
public class DeleteUserNotFoundException extends DomainException {

	public DeleteUserNotFoundException(String field, String value) {
		super(ErrorCode.DELETE_USER_NOT_FOUND, new ErrorDetail(field, value));
	}

	public DeleteUserNotFoundException() {
		super(ErrorCode.DELETE_USER_NOT_FOUND, new ErrorDetail());
	}
}