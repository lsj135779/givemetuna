package com.sparta.givemetuna.domain.card.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "카드에 대한 Assignee 권한이 없습니다."
public class CardAssigneeInvalidAuthorizationException extends DomainException {

	public CardAssigneeInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.CARD_ASSIGNEE_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public CardAssigneeInvalidAuthorizationException() {
		super(ErrorCode.CARD_ASSIGNEE_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}