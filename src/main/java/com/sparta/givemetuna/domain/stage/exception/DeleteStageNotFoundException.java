package com.sparta.givemetuna.domain.stage.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "삭제하시려는 스테이지는 존재하지 않습니다."
public class DeleteStageNotFoundException extends DomainException {

	public DeleteStageNotFoundException(String field, String value) {
		super(ErrorCode.DELETE_STAGE_NOT_FOUND, new ErrorDetail(field, value));
	}

	public DeleteStageNotFoundException() {
		super(ErrorCode.DELETE_STAGE_NOT_FOUND, new ErrorDetail());
	}
}