package com.sparta.givemetuna.domain.issue.repository.helper;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.sparta.givemetuna.domain.common.helper.QueryDslUtil;
import com.sparta.givemetuna.domain.issue.entity.QIssue;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

public final class IssueQueryOrderFactory {

	public static List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {

		List<OrderSpecifier> ORDERS = new ArrayList<>();

		if (!ObjectUtils.isEmpty(pageable.getSort())) {
			for (Sort.Order order : pageable.getSort()) {
				Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

				switch (order.getProperty()) {
					case "id" -> {
						OrderSpecifier<?> orderId = QueryDslUtil.getSortedColumn(direction, QIssue.issue, "id");
						ORDERS.add(orderId);
					}
					case "title" -> {
						OrderSpecifier<?> orderUser = QueryDslUtil.getSortedColumn(direction, QIssue.issue, "title");
						ORDERS.add(orderUser);
					}
					default -> {
					}
				}
			}
		}

		return ORDERS;
	}
}
