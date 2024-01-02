package com.sparta.givemetuna.domain.common.exception;

import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "잘못된 형식의 입력값입니다."
public class InvalidParamException extends DomainException {

	public InvalidParamException(String field, String value) {
		super(ErrorCode.ACCESS_DENIED, new ErrorDetail(field, value));
	}

	public InvalidParamException() {
		super(ErrorCode.ACCESS_DENIED, new ErrorDetail());
	}
}