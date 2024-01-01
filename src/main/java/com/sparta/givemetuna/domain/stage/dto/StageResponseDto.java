package com.sparta.givemetuna.domain.stage.dto;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StageResponseDto {
    private Long id;
    private String category;
    private List<Card> cards;

    public StageResponseDto(Stage stage) {
        this.id = stage.getId();
        this.category = stage.getCategory();
    }

//    public StageResponseDto(List<CardResponsDto> cardResponstDtos) {
//        this.cards = cardResponstDtos
//                .stream()
//                .map(StageResponseDto::new)
//                .toList();
//    }
}
