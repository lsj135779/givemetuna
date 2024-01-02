package com.sparta.givemetuna.domain.issue.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 이슈의 삭제 권한이 없습니다."
public class DeleteIssueInvalidAuthorizationException extends DomainException {

	public DeleteIssueInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.DELETE_ISSUE_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public DeleteIssueInvalidAuthorizationException() {
		super(ErrorCode.DELETE_ISSUE_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}