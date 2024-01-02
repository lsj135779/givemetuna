package com.sparta.givemetuna.domain.card.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "카드에 대한 Assignor 권한이 없습니다."
public class CardAssignorInvalidAuthorizationException extends DomainException {

	public CardAssignorInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.CARD_ASSIGNOR_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public CardAssignorInvalidAuthorizationException() {
		super(ErrorCode.CARD_ASSIGNOR_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}