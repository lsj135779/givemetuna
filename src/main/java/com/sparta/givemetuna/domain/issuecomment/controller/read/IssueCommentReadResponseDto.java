package com.sparta.givemetuna.domain.issuecomment.controller.read;


import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
public class IssueCommentReadResponseDto {

	private User user;

	private String contents;

	private Issue issue;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public static IssueCommentReadResponseDto of(IssueComment issueComment) {
		return new IssueCommentReadResponseDto(
			issueComment.getUser(),
			issueComment.getContents(),
			issueComment.getIssue(),
			issueComment.getCreatedAt(),
			issueComment.getUpdatedAt()
		);
	}
}
