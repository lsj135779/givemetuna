package com.sparta.givemetuna.domain.issue.dto.cud;

import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class IssueStatusUpdateResponseDto {

	private long issueId;

	private IssueStatus issueStatus;

	private LocalDateTime updatedAt;

	public static IssueStatusUpdateResponseDto of(Issue issue) {
		return new IssueStatusUpdateResponseDto(
			issue.getId(),
			issue.getIssueStatus(),
			issue.getUpdatedAt());
	}
}
