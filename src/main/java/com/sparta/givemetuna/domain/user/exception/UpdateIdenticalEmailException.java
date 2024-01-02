package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "이전과 동일한 이메일을 입력하셨습니다."
public class UpdateIdenticalEmailException extends DomainException {

	public UpdateIdenticalEmailException(String field, String value) {
		super(ErrorCode.UPDATE_IDENTICAL_EMAIL, new ErrorDetail(field, value));
	}

	public UpdateIdenticalEmailException() {
		super(ErrorCode.UPDATE_IDENTICAL_EMAIL, new ErrorDetail());
	}
}