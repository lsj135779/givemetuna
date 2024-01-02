package com.sparta.givemetuna.domain.checklist.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "찾으시는 체크리스트는 존재하지 않습니다."
public class SelectChecklistNotFoundException extends DomainException {

	public SelectChecklistNotFoundException(String field, String value) {
		super(ErrorCode.SELECT_CHECKLIST_NOT_FOUND, new ErrorDetail(field, value));
	}

	public SelectChecklistNotFoundException() {
		super(ErrorCode.SELECT_CHECKLIST_NOT_FOUND, new ErrorDetail());
	}
}