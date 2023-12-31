package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCardTitleRequestDto {

    @Size(min = 1 ,max = 500)
    @NotNull
    @NotBlank
    private String title;
}
