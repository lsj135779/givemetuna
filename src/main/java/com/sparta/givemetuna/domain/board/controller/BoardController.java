package com.sparta.givemetuna.domain.board.controller;

import com.sparta.givemetuna.domain.board.dto.*;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.service.BoardService;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.service.UserService;
import com.sparta.givemetuna.global.validator.BoardUserRoleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

	private final BoardService boardService;
	private final BoardUserRoleValidator boardUserRoleValidator;
	private final UserService userService;

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
	public ResponseEntity<InviteUserResponseDto> inviteUser(
			@PathVariable("board_id") Long boardId,
			@RequestBody InviteUserRequestDto requestDto,
			@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		// board 생성자만 초대가능
		boardUserRoleValidator.validateRole(BoardController.class, userDetails.getUser().getId(), boardId);
		Map<User, Role> users = userService.findbyAccountsWithRole(requestDto.getInvitations());
		Board board = boardService.inviteUser(boardId, users);
		InviteUserResponseDto responseDto = InviteUserResponseDto.of(board);
		return ResponseEntity.ok().body(responseDto);
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
	public ResponseEntity<BoardResponseDto> getBoard(@PathVariable(name = "boardId") Long boardId,
													 @AuthenticationPrincipal UserDetailsImpl userDetails)  {

		Board board = boardService.getBoard(boardId);
		BoardResponseDto responseDto = BoardResponseDto.of(board);
		return ResponseEntity.ok().body(responseDto);
	}

	// board 삭제
	// todo: user 권한 확인 필요
	@DeleteMapping("/{boardId}")
	public ResponseEntity<DeleteBoardResponseDto> deleteBoard(@PathVariable(name = "boardId") Long boardId,
															  @AuthenticationPrincipal UserDetailsImpl userDetails) {

		// board 생성자만 삭제 가능
		boardUserRoleValidator.validateRole(BoardController.class, userDetails.getUser().getId(), boardId);
		boardService.deleteBoard(boardId, userDetails.getUser());
		return ResponseEntity.ok().body(new DeleteBoardResponseDto("Board deleted successfully"));
	}

	// board 수정
	// TODO: 2023-12-29 user 권한 확인 기능 필요 
	@PatchMapping("/{boardId}")
	public ResponseEntity<UpdateBoardResponseDto> updateBoard(@PathVariable(name = "boardId") Long boardId,
															  @RequestBody UpdateBoardRequestDto requestDto,
															  @AuthenticationPrincipal UserDetailsImpl userDetails) {
		// board 생성자만 수정 가능
		boardUserRoleValidator.validateRole(BoardController.class, userDetails.getUser().getId(), boardId);
		Board board = boardService.updateBoard(boardId, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(new UpdateBoardResponseDto(board));
	}
}
