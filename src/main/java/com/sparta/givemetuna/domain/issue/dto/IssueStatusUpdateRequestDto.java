package com.sparta.givemetuna.domain.issue.dto;

import com.sparta.givemetuna.domain.issue.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IssueStatusUpdateRequestDto {

	private Status status;
}
