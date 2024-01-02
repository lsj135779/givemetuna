package com.sparta.givemetuna.domain.issue.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 이슈의 수정 권한이 없습니다."
public class UpdateIssueInvalidAuthorizationException extends DomainException {

	public UpdateIssueInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.UPDATE_ISSUE_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public UpdateIssueInvalidAuthorizationException() {
		super(ErrorCode.UPDATE_ISSUE_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}