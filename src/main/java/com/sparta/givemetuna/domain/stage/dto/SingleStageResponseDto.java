package com.sparta.givemetuna.domain.stage.dto;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SingleStageResponseDto {
    private Long id;
    private String category;
    private List<String> cards;

    public static SingleStageResponseDto of(Stage stage) {
        return new SingleStageResponseDto(
                stage.getId(),
                stage.getCategory(),
                stage.getUserCards().stream()
                        .map(Card::getTitle)
                        .toList()
        );
    }
}
