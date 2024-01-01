package com.sparta.givemetuna.domain.issue.repository.helper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.sparta.givemetuna.domain.issue.entity.QIssue;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

class IssueQueryOrderFactoryTest {

	@Test
	@DisplayName("Issue에 대한 Pageable 파라미터를 입력받아, List<OrderSpecifier>을 생성합니다.")
	public void 이슈테이블의_컬럼에대한_정렬리스트_생성() {
		// GIVEN
		Pageable pageable = PageRequest.of(
			0,
			1,
			Sort.by("title").descending()
				.and(Sort.by("id").ascending())
		);

		// WHEN
		List<OrderSpecifier> orderSpecifiers = IssueQueryOrderFactory.getAllOrderSpecifiers(pageable);

		// THEN
		boolean isDesc = orderSpecifiers.stream()
			.anyMatch(os ->
				os.getOrder().equals(Order.DESC));
		boolean hasIssueTitleOrder = orderSpecifiers.stream()
			.anyMatch(os ->
				os.getTarget().equals(QIssue.issue.title));
		boolean isAsc = orderSpecifiers.stream()
			.anyMatch(os ->
				os.getOrder().equals(Order.ASC));
		boolean hasIssueIdOrder = orderSpecifiers.stream()
			.anyMatch(os ->
				os.getTarget().equals(QIssue.issue.id));
		assertTrue(isDesc);
		assertTrue(hasIssueTitleOrder);
		assertTrue(isAsc);
		assertTrue(hasIssueIdOrder);
	}
}