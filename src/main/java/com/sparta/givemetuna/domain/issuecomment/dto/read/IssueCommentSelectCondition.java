package com.sparta.givemetuna.domain.issuecomment.dto.read;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IssueCommentSelectCondition {

	private long userId;

	private long issueId;

	private String contents;
}
