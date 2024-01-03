package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardStageRequestDto {

	@NotNull(message = "옮기실 Stage를 입력해주세요.")
	private Long stageId;
}
