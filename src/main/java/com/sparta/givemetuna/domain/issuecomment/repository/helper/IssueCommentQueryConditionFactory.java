package com.sparta.givemetuna.domain.issuecomment.repository.helper;

import static com.sparta.givemetuna.domain.issuecomment.entity.QIssueComment.issueComment;

import com.querydsl.core.types.dsl.BooleanExpression;

public final class IssueCommentQueryConditionFactory {

	public static BooleanExpression issueEq(Long issueId) {
		if (issueId == 0) {
			return null;
		}
		return issueComment.issue.id.eq(issueId);
	}

	public static BooleanExpression userEq(Long userId) {
		if (userId == 0) {
			return null;
		}
		return issueComment.user.id.eq(userId);
	}

	public static BooleanExpression contentsContains(String contents) {
		if (contents == null) {
			return null;
		}
		return issueComment.contents.contains(contents);
	}
}
