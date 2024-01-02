package com.sparta.givemetuna.domain.issuecomment.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "이슈댓글에 대한 올바른 정렬기준값을 입력해주세요."
public class SelectIssueCommentInvalidOrderCriteriaException extends DomainException {

	public SelectIssueCommentInvalidOrderCriteriaException(String field, String value) {
		super(ErrorCode.SELECT_ISSUE_COMMENT_INVALID_ORDER_CRITERIA, new ErrorDetail(field, value));
	}

	public SelectIssueCommentInvalidOrderCriteriaException() {
		super(ErrorCode.SELECT_ISSUE_COMMENT_INVALID_ORDER_CRITERIA, new ErrorDetail());
	}
}