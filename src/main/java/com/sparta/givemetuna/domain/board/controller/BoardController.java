package com.sparta.givemetuna.domain.board.controller;

import com.sparta.givemetuna.domain.board.dto.*;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    // board 생성
    @PostMapping
    public ResponseEntity<CreateBoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto requestDto) {
        Board board = boardService.createBoard(requestDto);
        CreateBoardResponseDto response = new CreateBoardResponseDto(board);
        return ResponseEntity.ok().body(response);
    }

    // user 추가하는 기능 필요
    @PostMapping("/{boardId}/invite")
    public void inviteUser(@RequestBody InviteUserRequestDto requestDto) {
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<DeleteBoardResponseDto> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok().body(new DeleteBoardResponseDto("Board deleted successfully"));
    }

    // 모든 board 조회
    // 현재 board 이름만 가져옴
    @GetMapping
    public ResponseEntity<List<BoardListResponseDto>> getBoards() {
        List<BoardListResponseDto> boardList = boardService.getAllBoards();
        return ResponseEntity.ok().body(boardList);
    }

    // board 단일 조회
    // 현재 board 이름만 가져옴
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
        Board board = boardService.getBoard(boardId);
        return ResponseEntity.ok().body(new BoardResponseDto(board));
    }

    // user 권한 확인 필요
    @PatchMapping("/{boardId}")
    public ResponseEntity<UpdateBoardResponseDto> updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequestDto requestDto) {
        Board board = boardService.updateBoard(boardId, requestDto);
        return ResponseEntity.ok().body(new UpdateBoardResponseDto(board));
    }
}
