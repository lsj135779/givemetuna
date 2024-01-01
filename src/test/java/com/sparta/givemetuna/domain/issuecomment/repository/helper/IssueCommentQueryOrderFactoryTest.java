package com.sparta.givemetuna.domain.issuecomment.repository.helper;

import com.querydsl.core.types.OrderSpecifier;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

class IssueCommentQueryOrderFactoryTest {

	@Test
	@DisplayName("IssueComment에 대한 Pageable 파라미터를 입력받아, List<OrderSpecifier>을 생성합니다.")
	public void 이슈댓글테이블의_컬럼에대한_정렬리스트_생성() {
		// GIVEN
		Pageable pageable = PageRequest.of(
			0,
			1,
			Sort.by("userId").descending()
		);

		// WHEN
		List<OrderSpecifier> orderSpecifiers = IssueCommentQueryOrderFactory.getAllOrderSpecifiers(pageable);

		// THEN
		for (OrderSpecifier o : orderSpecifiers) {
			System.out.println("o.getTarget() = " + o.getTarget());
			System.out.println("o.getOrder() = " + o.getOrder());
		}
	}
}