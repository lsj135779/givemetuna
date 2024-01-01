package com.sparta.givemetuna.board.service;

import com.sparta.givemetuna.board.IntegrationTest;
import com.sparta.givemetuna.domain.board.dto.CreateBoardRequestDto;
import com.sparta.givemetuna.domain.board.dto.CreateBoardResponseDto;
import com.sparta.givemetuna.domain.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class BoardServiceTest extends IntegrationTest {

    @Autowired
    protected BoardService boardService;

    @DisplayName("board 생성 테스트")
    @Test
    void createBoard() {
    }
}
