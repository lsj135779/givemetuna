package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "이전과 동일한 아이디를 입력하셨습니다."
public class UpdateIdenticalAccountException extends DomainException {

	public UpdateIdenticalAccountException(String field, String value) {
		super(ErrorCode.UPDATE_IDENTICAL_ACCOUNT, new ErrorDetail(field, value));
	}

	public UpdateIdenticalAccountException() {
		super(ErrorCode.UPDATE_IDENTICAL_ACCOUNT, new ErrorDetail());
	}
}