package com.sparta.givemetuna.domain.issue.dto.cud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IssueUpdateRequestDto {

	private String title;

	private String contents;

	private long cardId;

}
