package com.sparta.givemetuna.domain.card.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "Card 요청 처리 시, 총 관리자 혹은 팀 매니저만 접근할 수 있습니다."
public class CardInvalidAuthorizationException extends DomainException {

	public CardInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.CARD_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public CardInvalidAuthorizationException() {
		super(ErrorCode.CARD_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}