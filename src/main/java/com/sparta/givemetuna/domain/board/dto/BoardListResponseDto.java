package com.sparta.givemetuna.domain.board.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardListResponseDto {
    private String name;

    public BoardListResponseDto(String name) {
        this.name = name;
    }
}
