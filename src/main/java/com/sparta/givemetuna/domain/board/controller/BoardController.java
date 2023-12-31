package com.sparta.givemetuna.domain.board.controller;

import com.sparta.givemetuna.domain.board.dto.BoardListResponseDto;
import com.sparta.givemetuna.domain.board.dto.BoardResponseDto;
import com.sparta.givemetuna.domain.board.dto.CreateBoardRequestDto;
import com.sparta.givemetuna.domain.board.dto.CreateBoardResponseDto;
import com.sparta.givemetuna.domain.board.dto.DeleteBoardResponseDto;
import com.sparta.givemetuna.domain.board.dto.InviteUserRequestDto;
import com.sparta.givemetuna.domain.board.dto.UpdateBoardRequestDto;
import com.sparta.givemetuna.domain.board.dto.UpdateBoardResponseDto;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.service.BoardService;
import java.util.List;

import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
	// todo:생성한 사람이 권한을 가져야함
	@PostMapping
	public ResponseEntity<CreateBoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto requestDto,
															  @AuthenticationPrincipal UserDetailsImpl userDetails) {
		CreateBoardResponseDto responseDto = boardService.createBoard(requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(responseDto);
	}

	// user 초대
	// todo: user 추가하는 기능 필요
	@PostMapping("/{boardId}/invite")
	public void inviteUser(@RequestBody InviteUserRequestDto requestDto) {
	}

	// 모든 board 조회
	// todo:생성한 사람과 초대받은 사람 이외의 사람들에겐 접근권한 없어야함
	@GetMapping
	public ResponseEntity<List<BoardListResponseDto>> getBoards() {
		List<BoardListResponseDto> boardList = boardService.getAllBoards();
		return ResponseEntity.ok().body(boardList);
	}
	
	// board 단일 조회
	// todo: stage와 card까지 보여져야함
	// todo:생성한 사람과 초대받은 사람 이외의 사람들에겐 접근권한 없어야함
	@GetMapping("/{boardId}")
	public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
		Board board = boardService.getBoard(boardId);
		return ResponseEntity.ok().body(new BoardResponseDto(board));
	}
	
	// board 삭제
	// todo: user 권한 확인 필요
	@DeleteMapping("/{boardId}")
	public ResponseEntity<DeleteBoardResponseDto> deleteBoard(@PathVariable Long boardId,
															  @AuthenticationPrincipal UserDetailsImpl userDetails) {
		boardService.deleteBoard(boardId, userDetails.getUser());
		return ResponseEntity.ok().body(new DeleteBoardResponseDto("Board deleted successfully"));
	}

	// board 수정
	// TODO: 2023-12-29 user 권한 확인 기능 필요 
	@PatchMapping("/{boardId}")
	public ResponseEntity<UpdateBoardResponseDto> updateBoard(@PathVariable Long boardId,
															  @RequestBody UpdateBoardRequestDto requestDto,
															  @AuthenticationPrincipal UserDetailsImpl userDetails) {
		Board board = boardService.updateBoard(boardId, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(new UpdateBoardResponseDto(board));
	}
}
