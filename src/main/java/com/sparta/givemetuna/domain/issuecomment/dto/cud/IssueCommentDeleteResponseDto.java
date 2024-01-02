package com.sparta.givemetuna.domain.issuecomment.dto.cud;

import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class IssueCommentDeleteResponseDto {

	private long issueCommentId;

	public static IssueCommentDeleteResponseDto of(IssueComment issueComment) {
		return new IssueCommentDeleteResponseDto(issueComment.getId());
	}
}
