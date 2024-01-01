//package com.sparta.givemetuna.domain.board.dto;
//
//import com.sparta.givemetuna.domain.board.entity.Board;
//import com.sparta.givemetuna.domain.stage.dto.StageResponseDto;
//import lombok.Getter;
//
//import java.util.List;
//
//@Getter
//public class BoardDetailsResponseDto {
//
//    private final Long id;
//    private final String name;
//    private final String account;
//    private final List<StageResponseDto<List<CardResponsDto>>> stageResponseDtos;
//
//    public BoardDetailsResponseDto(Board board) {
//        this.id = board.getId();
//        this.name = board.getName();
//        this.account = board.getUser().getAccount();
//        this.stageResponseDtos = board.getStages()
//                .stream()
//                .map(StageResponseDto::new)
//                .toList();
//    }
//
//}
