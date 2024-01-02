package com.sparta.givemetuna.domain.stage.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 스테이지의 조회 권한이 없습니다."
public class SelectStageInvalidAuthorizationException extends DomainException {

	public SelectStageInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.SELECT_STAGE_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public SelectStageInvalidAuthorizationException() {
		super(ErrorCode.SELECT_STAGE_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}