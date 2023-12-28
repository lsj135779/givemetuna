package com.sparta.givemetuna.domain.board.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {
    private String name;

    public BoardResponseDto(Board board) {
        this.name = board.getName();
    }
}
