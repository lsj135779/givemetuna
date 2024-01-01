package com.sparta.givemetuna.domain.issue.dto.cud;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class IssueCreateResponseDto {

	private long issueId;

	private String title;

	private String contents;

	private long cardId;

	private LocalDateTime createdAt;

	public static IssueCreateResponseDto of(Issue issue) {
		return new IssueCreateResponseDto(
			issue.getId(),
			issue.getTitle(),
			issue.getContents(),
			issue.getCard().getId(),
			issue.getCreatedAt()
		);
	}
}