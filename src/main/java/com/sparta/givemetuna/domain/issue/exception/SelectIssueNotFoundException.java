package com.sparta.givemetuna.domain.issue.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "찾으시는 이슈는 존재하지 않습니다."
public class SelectIssueNotFoundException extends DomainException {

	public SelectIssueNotFoundException(String field, String value) {
		super(ErrorCode.SELECT_ISSUE_NOT_FOUND, new ErrorDetail(field, value));
	}

	public SelectIssueNotFoundException() {
		super(ErrorCode.SELECT_ISSUE_NOT_FOUND, new ErrorDetail());
	}
}