package com.sparta.givemetuna.domain.issuecomment.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 이슈 댓글의 삭제 권한이 없습니다."
public class DeleteIssueCommentInvalidAuthorizationException extends DomainException {

	public DeleteIssueCommentInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.DELETE_ISSUE_COMMENT_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public DeleteIssueCommentInvalidAuthorizationException() {
		super(ErrorCode.DELETE_ISSUE_COMMENT_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}