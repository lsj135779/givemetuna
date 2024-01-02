package com.sparta.givemetuna.domain.common.exception;

import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainException extends RuntimeException {

	private final ErrorCode errorCode;

	private ErrorDetail errorDetail;

	public DomainException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}