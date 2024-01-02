package com.sparta.givemetuna.domain.stage.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "수정하시려는 스테이지는 존재하지 않습니다."
public class UpdateStageNotFoundException extends DomainException {

	public UpdateStageNotFoundException(String field, String value) {
		super(ErrorCode.UPDATE_STAGE_NOT_FOUND, new ErrorDetail(field, value));
	}

	public UpdateStageNotFoundException() {
		super(ErrorCode.UPDATE_STAGE_NOT_FOUND, new ErrorDetail());
	}
}