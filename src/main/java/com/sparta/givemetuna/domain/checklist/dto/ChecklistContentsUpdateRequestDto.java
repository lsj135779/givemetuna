package com.sparta.givemetuna.domain.checklist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChecklistContentsUpdateRequestDto {

	@NotBlank(message = "내용을 입력해주세요.")
	private String contents;
}
