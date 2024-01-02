package com.sparta.givemetuna.domain.board.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sparta.givemetuna.domain.board.dto.QStageReadResponseDto is a Querydsl Projection type for StageReadResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStageReadResponseDto extends ConstructorExpression<StageReadResponseDto> {

    private static final long serialVersionUID = 738656608L;

    public QStageReadResponseDto(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<? extends java.util.List<com.sparta.givemetuna.domain.card.entity.Card>> cards) {
        super(StageReadResponseDto.class, new Class<?>[]{String.class, java.util.List.class}, category, cards);
    }

}

