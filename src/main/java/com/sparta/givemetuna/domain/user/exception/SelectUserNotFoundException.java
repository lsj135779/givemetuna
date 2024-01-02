package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "찾으시는 회원은 존재하지 않습니다."
public class SelectUserNotFoundException extends DomainException {

	public SelectUserNotFoundException(String field, String value) {
		super(ErrorCode.SELECT_USER_NOT_FOUND, new ErrorDetail(field, value));
	}

	public SelectUserNotFoundException() {
		super(ErrorCode.SELECT_USER_NOT_FOUND, new ErrorDetail());
	}
}