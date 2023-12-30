package com.sparta.givemetuna.domain.issue.dto.read;

import com.querydsl.core.annotations.QueryProjection;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueComment;
import com.sparta.givemetuna.domain.issue.entity.Status;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
public class IssueReadResponseDto {

	private long id;

	private String title;

	private String contents;

	private Status status;

	private long cardId;

	private List<IssueComment> issueComments;

	@QueryProjection
	public IssueReadResponseDto(long id, String title, String contents, Status status, long cardId, List<IssueComment> issueComments) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.status = status;
		this.cardId = cardId;
		this.issueComments = issueComments;
	}

	public static IssueReadResponseDto of(Issue issue) {
		return new IssueReadResponseDto(
			issue.getId(),
			issue.getTitle(),
			issue.getContents(),
			issue.getStatus(),
			issue.getCard().getId(),
			issue.getIssueComments()
		);
	}
}
