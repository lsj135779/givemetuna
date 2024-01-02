package com.sparta.givemetuna.domain.stage.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStageRequestDto {
    private String category;
    private Board board;
}
