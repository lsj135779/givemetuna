package com.sparta.givemetuna.domain.stage.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 스테이지의 삭제 권한이 없습니다."
public class DeleteStageInvalidAuthorizationException extends DomainException {

	public DeleteStageInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.DELETE_STAGE_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public DeleteStageInvalidAuthorizationException() {
		super(ErrorCode.DELETE_STAGE_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}