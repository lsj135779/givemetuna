package com.sparta.givemetuna.domain.card.dto.request;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdatetCardPriorityRequestDto {

    @NotNull
    @NotBlank
    private CardPriority cardPriority;
}
