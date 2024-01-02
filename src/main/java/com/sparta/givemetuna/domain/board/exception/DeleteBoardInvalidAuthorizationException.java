package com.sparta.givemetuna.domain.board.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 보드의 수정 권한이 없습니다."
public class DeleteBoardInvalidAuthorizationException extends DomainException {

	public DeleteBoardInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.DELETE_BOARD_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public DeleteBoardInvalidAuthorizationException() {
		super(ErrorCode.DELETE_BOARD_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}