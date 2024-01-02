package com.sparta.givemetuna.domain.checklist.dto;

import com.sparta.givemetuna.domain.checklist.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChecklistPriorityUpdateRequestDto {

	@NotBlank(message = "우선순위를 선택해주세요.")
	private Priority priority;
}
