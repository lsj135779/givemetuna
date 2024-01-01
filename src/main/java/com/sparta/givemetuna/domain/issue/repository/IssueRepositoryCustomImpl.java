package com.sparta.givemetuna.domain.issue.repository;

import static com.sparta.givemetuna.domain.card.entity.QCard.card;
import static com.sparta.givemetuna.domain.issue.entity.QIssue.issue;
import static com.sparta.givemetuna.domain.issuecomment.entity.QIssueComment.issueComment;
import static com.sparta.givemetuna.domain.user.entity.QUser.user;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.sparta.givemetuna.domain.configuration.QueryDslConfig;
import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.repository.helper.IssueQueryConditionFactory;
import com.sparta.givemetuna.domain.issue.repository.helper.IssueQueryOrderFactory;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IssueRepositoryCustomImpl implements IssueRepositoryCustom {

	private final QueryDslConfig qd;

	private static List<IssueReadResponseDto> getReadResponseDtosFrom(Stream<Issue> issues) {
		return issues
			.map(IssueReadResponseDto::of)
			.toList();
	}

	/**
	 * 조건에 따른  IssueReadResponseDto 리스트 SELECT 쿼리
	 *
	 * @param condition 이슈 조회 조건절
	 * @return JPA 쿼리문
	 */
	private JPAQuery<Issue> queryAllBy(IssueSelectCondition condition) {
		// Dynamic Query
		return qd.query()
			.selectFrom(issue)
			.leftJoin(issue.card, card) // fetch join of "issue" & "card"
			.leftJoin(issue.user, user) // fetch join of "issue" & "card"
			.leftJoin(issue.issueComments, issueComment) // fetch join of "issue" & "card"
			.where(
				IssueQueryConditionFactory.titleContains(condition.getTitle()),
				IssueQueryConditionFactory.contentsContains(condition.getContents()),
				IssueQueryConditionFactory.statusEq(condition.getIssueStatus())
			);
	}

	/**
	 * 조건에 따른 쿼리, Issue 리스트 결과값 반환
	 *
	 * @param condition 이슈 조회 조건절
	 * @return 조회 결과인 IssueReadResponseDto 리스트 반환
	 * @apiNote queryBy() 함수를 의존
	 */
	@Override
	public List<IssueReadResponseDto> selectByCondition(IssueSelectCondition condition) {
		// Dynamic Query
		JPAQuery<Issue> allBy = queryAllBy(condition);
		// Mapping :: Issue -> IssueReadResponseDto
		return getReadResponseDtosFrom(allBy.stream());
	}

	/**
	 * 조건에 따른 Issue 조회 이후, 이에 대한 페이징 처리하여 반환
	 *
	 * @param condition 이슈 조회 조건절
	 * @param pageable  페이징
	 * @return IssueReadResponseDto 의 Page 컬렉션
	 */
	@Override
	public Page<IssueReadResponseDto> selectByConditionPaging(IssueSelectCondition condition, Pageable pageable) {
		// Get Order By Columns
		OrderSpecifier[] orderSpecifiers = IssueQueryOrderFactory.getAllOrderSpecifiersArr(pageable);

		// Dynamic Query + Order By + Paging
		JPAQuery<Issue> issueJPAQuery = queryAllBy(condition);
		List<Issue> issues = issueJPAQuery
			.fetchJoin()
			.orderBy(orderSpecifiers)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
		// Mapping :: Issue -> IssueReadResponseDto
		List<IssueReadResponseDto> readResponseDtos = getReadResponseDtosFrom(issues.stream());

		return PageableExecutionUtils.getPage(readResponseDtos, pageable, () -> issueJPAQuery.fetch().size());
	}
}
