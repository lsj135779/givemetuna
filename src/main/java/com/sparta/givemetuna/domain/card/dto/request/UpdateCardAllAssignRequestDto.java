package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardAllAssignRequestDto {

    @NotBlank(message = "User의 Account를 입력해주세요")
    private String assignorAccount;

    @NotBlank(message = "User의 Account를 입력해주세요")
    private String assigneeAccount;
}
