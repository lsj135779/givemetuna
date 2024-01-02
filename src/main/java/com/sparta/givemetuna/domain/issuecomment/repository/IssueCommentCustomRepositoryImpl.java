package com.sparta.givemetuna.domain.issuecomment.repository;

import static com.sparta.givemetuna.domain.issue.entity.QIssue.issue;
import static com.sparta.givemetuna.domain.issuecomment.entity.QIssueComment.issueComment;
import static com.sparta.givemetuna.domain.user.entity.QUser.user;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.sparta.givemetuna.domain.issuecomment.controller.read.IssueCommentReadResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.read.IssueCommentSelectCondition;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.issuecomment.repository.helper.IssueCommentQueryConditionFactory;
import com.sparta.givemetuna.domain.issuecomment.repository.helper.IssueCommentQueryOrderFactory;
import com.sparta.givemetuna.global.config.QueryDslConfig;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IssueCommentCustomRepositoryImpl implements IssueCommentCustomRepository {

	private final QueryDslConfig qd;

	public static List<IssueCommentReadResponseDto> getReadResponseDtosFrom(Stream<IssueComment> issueComments) {
		return issueComments
			.map(IssueCommentReadResponseDto::of)
			.toList();
	}

	public List<IssueCommentReadResponseDto> selectByCondition(Long issueId, IssueCommentSelectCondition condition) {
		// Dynamic Query
		JPAQuery<IssueComment> commentJPAQuery = queryAllBy(issueId, condition);
		// Mapping :: IssueComment -> IssueCommentReadResponseDto
		return getReadResponseDtosFrom(commentJPAQuery.stream());
	}

	@Override
	public JPAQuery<IssueComment> queryAllBy(Long issueId, IssueCommentSelectCondition condition) {
		// Dynamic Query
		return qd.query()
			.selectFrom(issueComment)
			.leftJoin(issueComment.user, user)
			.leftJoin(issueComment.issue, issue)
			.where(
				IssueCommentQueryConditionFactory.userEq(condition.getUserId()),
				IssueCommentQueryConditionFactory.issueEq(issueId),
				IssueCommentQueryConditionFactory.contentsContains(condition.getContents())
			);
	}

	@Override
	public Page<IssueCommentReadResponseDto> selectByConditionPaging(Long issueId, IssueCommentSelectCondition condition,
		Pageable pageable) {
		// Get Order By Columns
		OrderSpecifier[] orderSpecifiers = IssueCommentQueryOrderFactory.getAllOrderSpecifiersArr(pageable);

		// Dynamic Query + Order + Paging
		JPAQuery<IssueComment> issueCommentJPAQuery = queryAllBy(issueId, condition);
		List<IssueComment> issueComments = issueCommentJPAQuery
			.orderBy(orderSpecifiers)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
		// Mapping :: IssueComment -> IssueCommentReadResponseDto
		List<IssueCommentReadResponseDto> readResponseDtos = getReadResponseDtosFrom(issueComments.stream());

		return PageableExecutionUtils.getPage(readResponseDtos, pageable, () -> issueCommentJPAQuery.fetch().size());
	}
}
