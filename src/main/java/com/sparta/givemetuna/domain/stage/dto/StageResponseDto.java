package com.sparta.givemetuna.domain.stage.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StageResponseDto {
    private Long id;
    private String category;
    private Board board;

    public StageResponseDto(Stage stage) {
        this.id = stage.getId();
        this.category = stage.getCategory();
        this.board = stage.getBoard();
    }

}
