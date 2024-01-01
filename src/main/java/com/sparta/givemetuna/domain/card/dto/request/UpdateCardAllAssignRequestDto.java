package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCardAllAssignRequestDto {

    @NotBlank
    private String assignor;

    @NotBlank
    private String assignee;
}
