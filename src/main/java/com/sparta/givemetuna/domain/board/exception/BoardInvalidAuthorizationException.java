package com.sparta.givemetuna.domain.board.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "보드의 권한이 없습니다."
public class BoardInvalidAuthorizationException extends DomainException {

	public BoardInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.BOARD_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public BoardInvalidAuthorizationException() {
		super(ErrorCode.BOARD_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}