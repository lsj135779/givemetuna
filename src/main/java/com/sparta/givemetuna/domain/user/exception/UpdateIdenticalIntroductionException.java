package com.sparta.givemetuna.domain.user.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import com.sparta.givemetuna.global.exception.ErrorCode;
import com.sparta.givemetuna.global.exception.ErrorDetail;

// "이전과 동일한 자기소개를 입력하셨습니다."
public class UpdateIdenticalIntroductionException extends DomainException {

	public UpdateIdenticalIntroductionException(String field, String value) {
		super(ErrorCode.UPDATE_IDENTICAL_INTRODUCTION, new ErrorDetail(field, value));
	}

	public UpdateIdenticalIntroductionException() {
		super(ErrorCode.UPDATE_IDENTICAL_INTRODUCTION, new ErrorDetail());
	}
}