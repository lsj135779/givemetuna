package com.sparta.givemetuna.domain.issue.dto.read;

import com.sparta.givemetuna.domain.issue.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IssueSelectCondition {

	private String title;

	private String contents;

	private Status status;

	private long userId;

	private long cardId;

}