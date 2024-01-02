package com.sparta.givemetuna.domain.common.exception;

import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "올바른 정렬기준값을 입력해주세요."
public class InvalidOrderCriteriaException extends DomainException {

	public InvalidOrderCriteriaException(String field, String value) {
		super(ErrorCode.INVALID_ORDER_CRITERIA, new ErrorDetail(field, value));
	}

	public InvalidOrderCriteriaException() {
		super(ErrorCode.INVALID_ORDER_CRITERIA, new ErrorDetail());
	}
}