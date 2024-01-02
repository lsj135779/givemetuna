package com.sparta.givemetuna.domain.issue.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 이슈의 종료 권한이 없습니다."
public class CloseIssueInvalidAuthorizationException extends DomainException {

	public CloseIssueInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.CLOSE_ISSUE_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public CloseIssueInvalidAuthorizationException() {
		super(ErrorCode.CLOSE_ISSUE_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}