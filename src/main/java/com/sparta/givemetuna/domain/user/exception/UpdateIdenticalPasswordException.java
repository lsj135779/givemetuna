package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "이전과 동일한 비밀번호를 입력하셨습니다."
public class UpdateIdenticalPasswordException extends DomainException {

	public UpdateIdenticalPasswordException(String field, String value) {
		super(ErrorCode.UPDATE_IDENTICAL_PASSWORD, new ErrorDetail(field, value));
	}

	public UpdateIdenticalPasswordException() {
		super(ErrorCode.UPDATE_IDENTICAL_PASSWORD, new ErrorDetail());
	}
}