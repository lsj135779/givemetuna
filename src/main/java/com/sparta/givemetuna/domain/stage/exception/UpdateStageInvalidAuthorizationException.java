package com.sparta.givemetuna.domain.stage.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 스테이지의 수정 권한이 없습니다."
public class UpdateStageInvalidAuthorizationException extends DomainException {

	public UpdateStageInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.UPDATE_STAGE_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public UpdateStageInvalidAuthorizationException() {
		super(ErrorCode.UPDATE_STAGE_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}