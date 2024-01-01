package com.sparta.givemetuna.domain.issuecomment.dto.cud;


import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class IssueCommentUpdateResponseDto {

	private long userId;

	private long issueId;

	private String contents;

	private LocalDateTime updatedAt;

	public static IssueCommentUpdateResponseDto of(IssueComment issueComment) {
		return new IssueCommentUpdateResponseDto(
			issueComment.getUser().getId(),
			issueComment.getIssue().getId(),
			issueComment.getContents(),
			issueComment.getUpdatedAt()
		);
	}
}
