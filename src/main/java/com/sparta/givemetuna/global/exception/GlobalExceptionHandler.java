package com.sparta.givemetuna.global.exception;

import com.sparta.givemetuna.domain.common.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j(topic = "Exception Handler")
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다. HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생 주로
	 *
	 * @RequestBody, @RequestPart 어노테이션에서 발생
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> processValidationError(
		MethodArgumentNotValidException ex) {
		log.error("handleMethodArgumentNotValidException", ex);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_PARAM, ex.getBindingResult());
		return new ResponseEntity<>(response, ErrorCode.INVALID_PARAM.getCode());
	}


	/**
	 * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
	 */
	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
		log.error("handleAccessDeniedException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.ACCESS_DENIED);
		return new ResponseEntity<>(response, ErrorCode.ACCESS_DENIED.getCode());
	}

	/**
	 * 1. 도메인 관련 검증 실패 예외 케이스 2. 도메인 로직 수행 중의 예외 케이스 발생함
	 */
	@ExceptionHandler(DomainException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(final DomainException e) {
		log.error("handleDomainException", e);
		final ErrorCode errorCode = e.getErrorCode();
		final ErrorResponse response = ErrorResponse.of(errorCode, e.getErrorDetail());
		return new ResponseEntity<>(response, errorCode.getCode());
	}
}
