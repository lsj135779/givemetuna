package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardTitleRequestDto {

    @Size(min = 1 ,max = 500)
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
}
