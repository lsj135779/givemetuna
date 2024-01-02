package com.sparta.givemetuna.domain.issue.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "이슈에 대한 올바른 정렬기준값을 입력해주세요."
public class SelectIssueInvalidOrderCriteriaException extends DomainException {

	public SelectIssueInvalidOrderCriteriaException(String field, String value) {
		super(ErrorCode.SELECT_ISSUE_INVALID_ORDER_CRITERIA, new ErrorDetail(field, value));
	}

	public SelectIssueInvalidOrderCriteriaException() {
		super(ErrorCode.SELECT_ISSUE_INVALID_ORDER_CRITERIA, new ErrorDetail());
	}
}