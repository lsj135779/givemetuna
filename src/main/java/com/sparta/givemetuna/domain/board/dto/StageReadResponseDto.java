package com.sparta.givemetuna.domain.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class StageReadResponseDto {
    private String category;

    private List<Card> cards;

    @QueryProjection
    public StageReadResponseDto(String category, List<Card> cards) {
        this.category = category;
        this.cards = cards;
    }

    public static StageReadResponseDto of(Stage stage) {
        return new StageReadResponseDto(
                stage.getCategory(),
                stage.getUserCards()
        );
    }
}
