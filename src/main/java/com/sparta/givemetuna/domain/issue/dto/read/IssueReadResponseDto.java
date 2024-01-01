package com.sparta.givemetuna.domain.issue.dto.read;

import com.querydsl.core.annotations.QueryProjection;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
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

	private IssueStatus issueStatus;

	private long cardId;

	private List<IssueComment> issueComments;

	@QueryProjection
	public IssueReadResponseDto(long id, String title, String contents, IssueStatus issueStatus, long cardId,
		List<IssueComment> issueComments) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.issueStatus = issueStatus;
		this.cardId = cardId;
		this.issueComments = issueComments;
	}

	public static IssueReadResponseDto of(Issue issue) {
		return new IssueReadResponseDto(
			issue.getId(),
			issue.getTitle(),
			issue.getContents(),
			issue.getIssueStatus(),
			issue.getCard().getId(),
			issue.getIssueComments()
		);
	}
}
