package com.sparta.givemetuna.domain.checklist.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 체크리스트의 수정 권한이 없습니다."
public class UpdateChecklistInvalidAuthorizationException extends DomainException {

	public UpdateChecklistInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.UPDATE_CHECKLIST_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public UpdateChecklistInvalidAuthorizationException() {
		super(ErrorCode.UPDATE_CHECKLIST_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}