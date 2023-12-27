package com.sparta.givemetuna.domain.board.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBoardResponseDto {
    private Long id;
    private String name;

    public UpdateBoardResponseDto(Board board) {
        this.id = board.getId();
        this.name = board.getName();
    }
}
