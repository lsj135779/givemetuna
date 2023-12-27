package com.sparta.givemetuna.domain.board.controller;

import com.sparta.givemetuna.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    private void createBoard() {

    }

    @PostMapping("/{board_id}/invite")
    private void inviteUserToBoard() {

    }

    @PatchMapping("/{board_id}")
    private void deleteBoard() {

    }

    @GetMapping
    private void getBoards() {

    }

    @GetMapping("/{board_id}")
    private void getBoard() {

    }

}
