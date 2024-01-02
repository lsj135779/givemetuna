package com.sparta.givemetuna.domain.board.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "보드의 생성 권한이 없습니다."
public class CreateBoardInvalidAuthorizationException extends DomainException {

	public CreateBoardInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.CREATE_BOARD_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public CreateBoardInvalidAuthorizationException() {
		super(ErrorCode.CREATE_BOARD_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}