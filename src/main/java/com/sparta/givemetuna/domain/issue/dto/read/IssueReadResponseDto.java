package com.sparta.givemetuna.domain.issue.dto.read;

import com.querydsl.core.annotations.QueryProjection;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@ToString
public class IssueReadResponseDto {

	private long id;

	private String title;

	private String contents;

	private Status status;

	private long cardId;

	@QueryProjection
	public IssueReadResponseDto(long id, String title, String contents, Status status, long cardId) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.status = status;
		this.cardId = cardId;
	}

	public static IssueReadResponseDto of(Issue issue) {
		return new IssueReadResponseDto(
			issue.getId(),
			issue.getTitle(),
			issue.getContents(),
			issue.getStatus(),
			issue.getCard().getId()
		);
	}
}
