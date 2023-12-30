package com.sparta.givemetuna.domain.issue.repository;

import static com.sparta.givemetuna.domain.issue.entity.QIssue.issue;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.sparta.givemetuna.domain.configuration.QueryDslConfig;
import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.dto.read.QIssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.repository.helper.IssueQueryConditionFactory;
import com.sparta.givemetuna.domain.issue.repository.helper.IssueQueryOrderFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class IssueRepositoryCustomImpl implements IssueRepositoryCustom {

	private final QueryDslConfig qd;

	@Override
	public List<IssueReadResponseDto> selectByCondition(IssueSelectCondition condition) {
		// Dynamic Query Fetch
		return getJpaQuery(condition)
			.fetch();
	}

	@Override
	public Page<IssueReadResponseDto> selectByConditionPaging(IssueSelectCondition condition, Pageable pageable) {
		// Dynamic Query + Paging
		JPAQuery<IssueReadResponseDto> issuesQuery = getJpaQuery(condition)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize());

		// Order By
		OrderSpecifier[] orderSpecifiers = IssueQueryOrderFactory
			.getAllOrderSpecifiers(pageable)
			.toArray(OrderSpecifier[]::new);
		issuesQuery.orderBy(orderSpecifiers);

		// Count Query
		List<IssueReadResponseDto> issues = issuesQuery.fetch();
		JPAQuery<Issue> countQuery = qd.query()
			.selectFrom(issue)
			.leftJoin(issue.card)
			.leftJoin(issue.user)
			.where(
				IssueQueryConditionFactory.titleLike(condition.getTitle()),
				IssueQueryConditionFactory.contentsLike(condition.getContents()),
				IssueQueryConditionFactory.statusEq(condition.getStatus()),
				IssueQueryConditionFactory.userEq(condition.getUserId()),
				IssueQueryConditionFactory.cardEq(condition.getCardId())
			);

		return PageableExecutionUtils.getPage(issues, pageable, () -> countQuery.fetch().size());
	}

	private JPAQuery<IssueReadResponseDto> getJpaQuery(IssueSelectCondition condition) {
		// Dynamic Query
		return qd.query()
			.select(
				new QIssueReadResponseDto(
					issue.id,
					issue.title,
					issue.contents,
					issue.status,
					issue.card.id
				)
			)
			.from(issue)
			.leftJoin(issue.card)
			.leftJoin(issue.user)
			.leftJoin(issue.issueComments)
			.where(
				IssueQueryConditionFactory.titleLike(condition.getTitle()),
				IssueQueryConditionFactory.contentsLike(condition.getContents()),
				IssueQueryConditionFactory.statusEq(condition.getStatus()),
				IssueQueryConditionFactory.userEq(condition.getUserId()),
				IssueQueryConditionFactory.cardEq(condition.getCardId())
			);
	}

	private JPAQuery<Issue> selectBy(IssueSelectCondition condition) {
		// Dynamic Query
		return qd.query()
			.selectFrom(issue)
			.leftJoin(issue.card)
			.leftJoin(issue.user)
			.leftJoin(issue.issueComments)
			.where(
				IssueQueryConditionFactory.titleLike(condition.getTitle()),
				IssueQueryConditionFactory.contentsLike(condition.getContents()),
				IssueQueryConditionFactory.statusEq(condition.getStatus()),
				IssueQueryConditionFactory.userEq(condition.getUserId()),
				IssueQueryConditionFactory.cardEq(condition.getCardId())
			);
	}
}
