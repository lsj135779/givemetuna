package com.sparta.givemetuna.domain.issuecomment.dto.cud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IssueCommentUpdateRequestDto {

	private String contents;
}
