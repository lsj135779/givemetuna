package com.sparta.givemetuna.domain.checklist.dto;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.checklist.entity.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistContentsUpdateResponseDto {

	private Long id;

	private Long user_id;

	private Long card_id;

	private String contents;

	private Boolean check;

	private Priority priority;

	public static ChecklistContentsUpdateResponseDto of(Checklist checklist) {
		return new ChecklistContentsUpdateResponseDto(
			checklist.getId(),
			checklist.getAssignee().getId(),
			checklist.getCard().getId(),
			checklist.getContents(),
			checklist.getCheck(),
			checklist.getPriority()
		);
	}
}
