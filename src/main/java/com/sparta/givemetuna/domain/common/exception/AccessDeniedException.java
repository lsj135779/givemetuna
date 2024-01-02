package com.sparta.givemetuna.domain.common.exception;

import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "권한이 필요한 요청입니다."
public class AccessDeniedException extends DomainException {
	
	public AccessDeniedException(String field, String value) {
		super(ErrorCode.INVALID_PARAM, new ErrorDetail(field, value));
	}

	public AccessDeniedException() {
		super(ErrorCode.INVALID_PARAM, new ErrorDetail());
	}
}