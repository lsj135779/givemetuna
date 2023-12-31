package com.sparta.givemetuna.domain.issue.dto.cud;

import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IssueStatusUpdateRequestDto {

	private IssueStatus issueStatus;
}
