package com.sparta.givemetuna.domain.issue.repository.helper;

import static com.sparta.givemetuna.domain.issue.entity.QIssue.issue;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import java.util.Objects;

public final class IssueQueryConditionFactory {

	public static BooleanExpression cardEq(long cardId) {
		if (Objects.isNull(cardId)) {
			return null;
		}
		return issue.card.id.eq(cardId);
	}

	public static BooleanExpression userEq(long userId) {
		if (Objects.isNull(userId)) {
			return null;
		}
		return issue.user.id.eq(userId);
	}

	public static BooleanExpression statusEq(IssueStatus issueStatus) {
		if (issueStatus == null) {
			return null;
		}
		return issue.issueStatus.eq(issueStatus);
	}

	public static BooleanExpression contentsLike(String contents) {
		if (contents == null) {
			return null;
		}
		return issue.contents.like(contents);
	}

	public static BooleanExpression titleLike(String title) {
		if (title == null) {
			return null;
		}
		return issue.title.like(title);
	}
}
