package com.sparta.givemetuna.domain.board.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "해당 보드의 수정 권한이 없습니다."
public class UpdateBoardInvalidAuthorizationException extends DomainException {

	public UpdateBoardInvalidAuthorizationException(String field, String value) {
		super(ErrorCode.UPDATE_BOARD_INVALID_AUTHORIZATION, new ErrorDetail(field, value));
	}

	public UpdateBoardInvalidAuthorizationException() {
		super(ErrorCode.UPDATE_BOARD_INVALID_AUTHORIZATION, new ErrorDetail());
	}
}