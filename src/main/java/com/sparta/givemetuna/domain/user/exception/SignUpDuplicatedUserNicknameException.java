package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "이미 존재하는 닉네임입니다."
public class SignUpDuplicatedUserNicknameException extends DomainException {

	public SignUpDuplicatedUserNicknameException(String field, String value) {
		super(ErrorCode.SIGNUP_DUPLICATED_USER_NICKNAME, new ErrorDetail(field, value));
	}
	public SignUpDuplicatedUserNicknameException() {
		super(ErrorCode.SIGNUP_DUPLICATED_USER_NICKNAME, new ErrorDetail());
	}
}