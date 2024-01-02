package com.sparta.givemetuna.domain.card.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "카드에 대한 올바른 형식의 정렬기준값을 입력해주세요."
public class SelectCardInvalidOrderCriteriaException extends DomainException {

	public SelectCardInvalidOrderCriteriaException(String field, String value) {
		super(ErrorCode.SELECT_CARD_INVALID_ORDER_CRITERIA, new ErrorDetail(field, value));
	}

	public SelectCardInvalidOrderCriteriaException() {
		super(ErrorCode.SELECT_CARD_INVALID_ORDER_CRITERIA, new ErrorDetail());
	}
}