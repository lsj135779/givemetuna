package com.sparta.givemetuna.domain.board.controller;

import com.sparta.givemetuna.domain.board.dto.BoardListResponseDto;
import com.sparta.givemetuna.domain.board.dto.BoardResponseDto;
import com.sparta.givemetuna.domain.board.dto.CreateBoardRequestDto;
import com.sparta.givemetuna.domain.board.dto.CreateBoardResponseDto;
import com.sparta.givemetuna.domain.board.dto.DeleteBoardResponseDto;
import com.sparta.givemetuna.domain.board.dto.InviteUserRequestDto;
import com.sparta.givemetuna.domain.board.dto.InviteUserResponseDto;
import com.sparta.givemetuna.domain.board.dto.UpdateBoardRequestDto;
import com.sparta.givemetuna.domain.board.dto.UpdateBoardResponseDto;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<InviteUserResponseDto> inviteUser(@RequestBody InviteUserRequestDto requestDto) {
		InviteUserResponseDto reponseDto = boardService.inviteUser(requestDto);
		return ResponseEntity.ok().body(reponseDto);
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

	@DeleteMapping("/{boardId}")
	public ResponseEntity<DeleteBoardResponseDto> deleteBoard(@PathVariable Long boardId) {
		boardService.deleteBoard(boardId);
		return ResponseEntity.ok().body(new DeleteBoardResponseDto("Board deleted successfully"));
	}

	@PatchMapping("/{boardId}")
	public ResponseEntity<UpdateBoardResponseDto> updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequestDto requestDto) {
		Board board = boardService.updateBoard(boardId, requestDto);
		return ResponseEntity.ok().body(new UpdateBoardResponseDto(board));
	}
}
