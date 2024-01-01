package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCardStageRequestDto {

    @NotBlank
    private Long stageId;
}
