package com.sparta.givemetuna.domain.board.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 보드의 조회 권한이 없습니다."
public class SelectBoardInvalidAuthorizationException extends DomainException {

	public SelectBoardInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.SELECT_BOARD_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public SelectBoardInvalidAuthorizationException() {
		super(ErrorCode.SELECT_BOARD_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}