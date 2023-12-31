package com.sparta.givemetuna.domain.stage.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStageResponseDto {
    private Long id;
    private String category;
    private Board board;

    public CreateStageResponseDto(Stage stage) {
        this.id = stage.getId();
        this.category = stage.getCategory();
        this.board = stage.getBoard();
    }
}
