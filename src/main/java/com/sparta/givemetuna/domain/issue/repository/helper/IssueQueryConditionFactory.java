package com.sparta.givemetuna.domain.issue.repository.helper;

import static com.sparta.givemetuna.domain.issue.entity.QIssue.issue;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;

public final class IssueQueryConditionFactory {

	public static BooleanExpression cardEq(Long cardId) {
		if (cardId == 0) {
			return null;
		}
		return issue.card.id.eq(cardId);
	}

	public static BooleanExpression userEq(Long userId) {
		if (userId == 0) {
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

	public static BooleanExpression contentsContains(String contents) {
		if (contents == null) {
			return null;
		}
		return issue.contents.contains(contents);
	}

	public static BooleanExpression titleContains(String title) {
		if (title == null) {
			return null;
		}
		return issue.title.contains(title);
	}
}
