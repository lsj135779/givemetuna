package com.sparta.givemetuna.domain.checklist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChecklistCreateRequestDto {

	@NotBlank(message = "내용을 입력해주세요.")
	private String contents;

	// 테스트를 위해서 사용
	public ChecklistCreateRequestDto(String contents) {
		this.contents = contents;
	}
}
