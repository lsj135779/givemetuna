package com.sparta.givemetuna.domain.board.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "찾으시는 보드는 존재하지 않습니다."
public class SelectBoardNotFoundException extends DomainException {

	public SelectBoardNotFoundException(String field, String value) {
		super(ErrorCode.SELECT_BOARD_NOT_FOUND, new ErrorDetail(field, value));
	}

	public SelectBoardNotFoundException() {
		super(ErrorCode.SELECT_BOARD_NOT_FOUND, new ErrorDetail());
	}
}