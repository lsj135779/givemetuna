package com.sparta.givemetuna.domain.card.dto.request;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardPriorityRequestDto {

	@NotNull
	private CardPriority cardPriority;
}
