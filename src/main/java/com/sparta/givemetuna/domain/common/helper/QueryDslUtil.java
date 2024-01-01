package com.sparta.givemetuna.domain.common.helper;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;

public final class QueryDslUtil {

	public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
		Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
		return new OrderSpecifier(order, fieldPath);
	}
}
