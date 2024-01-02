package com.sparta.givemetuna.domain.checklist.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 체크리스트의 삭제 권한이 없습니다."
public class DeleteChecklistInvalidAuthorizationException extends DomainException {

	public DeleteChecklistInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.DELETE_CHECKLIST_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public DeleteChecklistInvalidAuthorizationException() {
		super(ErrorCode.DELETE_CHECKLIST_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}