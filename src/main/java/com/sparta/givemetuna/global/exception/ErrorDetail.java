package com.sparta.givemetuna.global.exception;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {

	private String field;

	private String value;

	private String reason;

	public ErrorDetail(String field, String value) {
		this.field = field;
		this.value = value;
	}

	public static List<ErrorDetail> of(BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		return fieldErrors.stream()
			.map(fieldError ->
				new ErrorDetail(fieldError.getField(),
					Objects.requireNonNull(fieldError.getRejectedValue()).toString(),
					fieldError.getDefaultMessage()))
			.toList();
	}

}
