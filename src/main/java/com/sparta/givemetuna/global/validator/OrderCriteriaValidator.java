package com.sparta.givemetuna.global.validator;

import com.sparta.givemetuna.domain.common.exception.InvalidOrderCriteriaException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class OrderCriteriaValidator {

	public static void validateOrderCriteria(Class<?> target, List<String> orderCriterias) {
		if (Objects.isNull(orderCriterias)) {
			return;
		}
		List<String> fields = Arrays.stream(target.getDeclaredFields())
			.map(Field::getName)
			.toList();
		boolean hasNotOrderCriteria = orderCriterias.stream()
			.anyMatch(criteria -> !fields.contains(criteria));
		if (hasNotOrderCriteria) {
			throw new InvalidOrderCriteriaException();
		}
	}
}
