package com.sparta.givemetuna.domain.board.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "삭제하시려는 보드는 존재하지 않습니다."
public class DeleteBoardNotFoundException extends DomainException {

	public DeleteBoardNotFoundException(String field, String value) {
		super(ErrorCode.DELETE_BOARD_NOT_FOUND, new ErrorDetail(field, value));
	}

	public DeleteBoardNotFoundException() {
		super(ErrorCode.DELETE_BOARD_NOT_FOUND, new ErrorDetail());
	}
}