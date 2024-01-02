package com.sparta.givemetuna.domain.stage.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "찾으시는 스테이지는 존재하지 않습니다."
public class SelectStageNotFoundException extends DomainException {

	public SelectStageNotFoundException(String field, String value) {
		super(ErrorCode.SELECT_STAGE_NOT_FOUND, new ErrorDetail(field, value));
	}

	public SelectStageNotFoundException() {
		super(ErrorCode.SELECT_STAGE_NOT_FOUND, new ErrorDetail());
	}
}