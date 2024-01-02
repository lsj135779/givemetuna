package com.sparta.givemetuna.global.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sparta.givemetuna.domain.common.exception.InvalidOrderCriteriaException;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.global.exception.ErrorCode;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderCriteriaValidatorTest {

	@Test
	@DisplayName("Issue의 정렬기준이 Issue 필드인지 검증합니다.")
	public void Issue정렬기준검증() {
		// GIVEN
		List<String> orderCriterias = List.of("id", "title");

		// WHEN
		// THEN
		OrderCriteriaValidator.validateOrderCriteria(Issue.class, orderCriterias);
	}

	@Test
	@DisplayName("Issue의 정렬기준이 Issue 필드가 아닌 경우 예외처리를 합니다.")
	public void Issue정렬기준검증_예외처리() {
		// GIVEN
		List<String> orderCriterias = List.of("wrong", "field");

		// WHEN
		// THEN
		InvalidOrderCriteriaException exception = assertThrows(
			InvalidOrderCriteriaException.class,
			() -> OrderCriteriaValidator.validateOrderCriteria(Issue.class, orderCriterias));
		assertEquals(ErrorCode.INVALID_ORDER_CRITERIA, exception.getErrorCode());
	}
}