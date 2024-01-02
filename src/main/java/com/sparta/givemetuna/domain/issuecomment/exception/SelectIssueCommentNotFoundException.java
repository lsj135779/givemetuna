package com.sparta.givemetuna.domain.issuecomment.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "찾으시는 이슈 댓글은 존재하지 않습니다."
public class SelectIssueCommentNotFoundException extends DomainException {

	public SelectIssueCommentNotFoundException(String field, String value) {
		super(ErrorCode.SELECT_ISSUE_COMMENT_NOT_FOUND, new ErrorDetail(field, value));
	}

	public SelectIssueCommentNotFoundException() {
		super(ErrorCode.SELECT_ISSUE_COMMENT_NOT_FOUND, new ErrorDetail());
	}
}