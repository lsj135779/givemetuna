package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import lombok.Getter;

@Getter
public class UpdateCardPeriodRequestDto {

	@NotNull
	private Timestamp startedAt;

	@NotNull
	private Timestamp closedAt;
}
