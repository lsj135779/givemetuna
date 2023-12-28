package com.sparta.givemetuna.domain.column.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStageResponseDto {
    private Long id;
    private String category;
    private Board board;
}
