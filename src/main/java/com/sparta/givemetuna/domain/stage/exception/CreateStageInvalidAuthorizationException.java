package com.sparta.givemetuna.domain.stage.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "스테이지의 생성 권한이 없습니다."
public class CreateStageInvalidAuthorizationException extends DomainException {

	public CreateStageInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.CREATE_STAGE_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public CreateStageInvalidAuthorizationException() {
		super(ErrorCode.CREATE_STAGE_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}