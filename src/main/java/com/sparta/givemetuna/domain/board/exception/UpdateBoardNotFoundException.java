package com.sparta.givemetuna.domain.board.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "수정하시려는 보드는 존재하지 않습니다."
public class UpdateBoardNotFoundException extends DomainException {

	public UpdateBoardNotFoundException(String field, String value) {
		super(ErrorCode.UPDATE_BOARD_NOT_FOUND, new ErrorDetail(field, value));
	}

	public UpdateBoardNotFoundException() {
		super(ErrorCode.UPDATE_BOARD_NOT_FOUND, new ErrorDetail());
	}
}