package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCardTitleRequestDto {

    @Size(max = 500)
    private String title;
}
