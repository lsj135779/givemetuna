package com.sparta.givemetuna.domain.checklist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChecklistDeleteResponseDto {

	private Long id;

	public static ChecklistDeleteResponseDto of(Long checklistId) {
		return new ChecklistDeleteResponseDto(
			checklistId
		);
	}
}
