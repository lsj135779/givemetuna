package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCardStageRequestDto {

	@NotNull
	private Long stageId;
}
