package com.sparta.givemetuna.domain.issue.dto.cud;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class IssueDeleteResponseDto {

	private long issueId;

	public static IssueDeleteResponseDto of(Long id) {
		return new IssueDeleteResponseDto(
			id
		);
	}
}
