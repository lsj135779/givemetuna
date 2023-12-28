package com.sparta.givemetuna.domain.board.service;

import com.sparta.givemetuna.domain.board.dto.*;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // board 생성
    public Board createBoard(CreateBoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return board;
    }

    // user 검증 추가 필요
    public void deleteBoard(Long boardId) {
        //권한을 체크하는 메서드

        Board board = getBoard(boardId);
        boardRepository.delete(board);
    }

    // 단일 board 찾기
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("The board does not exist"));
    }

    // 모든 board 찾기
    public List<BoardListResponseDto> getAllBoards() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(board -> new BoardListResponseDto(board.getName()))
                .collect(Collectors.toList());
    }

    // user 검증 로직 필요
    @Transactional
    public Board updateBoard(Long boardId, UpdateBoardRequestDto requestDto) {
        Board board = getBoard(boardId);

        // user 검증 로직

        board.setName(requestDto.getName());
        return board;
    }
}
