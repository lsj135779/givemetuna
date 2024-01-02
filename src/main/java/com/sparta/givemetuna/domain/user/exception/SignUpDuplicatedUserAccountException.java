package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "이미 존재하는 아이디입니다."
public class SignUpDuplicatedUserAccountException extends DomainException {

	public SignUpDuplicatedUserAccountException(String field, String value) {
		super(ErrorCode.SIGNUP_DUPLICATED_USER_ACCOUNT, new ErrorDetail(field, value));
	}

	public SignUpDuplicatedUserAccountException() {
		super(ErrorCode.SIGNUP_DUPLICATED_USER_ACCOUNT, new ErrorDetail());
	}
}