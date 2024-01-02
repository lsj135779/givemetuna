package com.sparta.givemetuna.domain.board.dto;

import com.sparta.givemetuna.domain.card.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardReadResponseDto {
    private String title;
    private String nickname;

    public CardReadResponseDto(String title, String nickname) {
        this.title = title;
        this.nickname = nickname;
    }

    public static CardReadResponseDto of(Card card) {
        return new CardReadResponseDto(
                card.getTitle(),
                card.getAssignor().getNickname()
        );
    }
}
