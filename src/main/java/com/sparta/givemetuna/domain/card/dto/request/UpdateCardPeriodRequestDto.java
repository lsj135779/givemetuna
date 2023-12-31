package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import lombok.Getter;

@Getter
public class UpdateCardPeriodRequestDto {

    @NotNull
    @NotBlank
    private Timestamp startedAt;

    @NotNull
    @NotBlank
    private Timestamp closedAt;
}
