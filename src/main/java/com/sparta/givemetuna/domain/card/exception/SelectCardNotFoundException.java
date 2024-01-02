package com.sparta.givemetuna.domain.card.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "카드에 대한 Assignee 권한이 없습니다."
public class SelectCardNotFoundException extends DomainException {

	public SelectCardNotFoundException(String field, String value) {
		super(ErrorCode.SELECT_CARD_NOT_FOUND, new ErrorDetail(field, value));
	}

	public SelectCardNotFoundException() {
		super(ErrorCode.SELECT_CARD_NOT_FOUND, new ErrorDetail());
	}
}