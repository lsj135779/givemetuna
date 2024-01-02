package com.sparta.givemetuna.domain.checklist.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 체크리스트의 삭제 권한이 없습니다."
public class SelectChecklistNotFoundException extends DomainException {

	public SelectChecklistNotFoundException(String field, String value) {
		super(ErrorCode.SELECT_CHECKLIST_NOT_FOUND, new ErrorDetail(field, value));
	}

	public SelectChecklistNotFoundException() {
		super(ErrorCode.SELECT_CHECKLIST_NOT_FOUND, new ErrorDetail());
	}
}