package com.sparta.givemetuna.global.validator;

import com.sparta.givemetuna.domain.board.controller.BoardController;
import com.sparta.givemetuna.domain.board.service.BoardService;
import com.sparta.givemetuna.domain.card.controller.CardController;
import com.sparta.givemetuna.domain.card.service.CardService;
import com.sparta.givemetuna.domain.core.service.CardMatcherService;
import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.repository.BoardUserRoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardUserRoleValidator {

	private final BoardUserRoleRepository boardUserRoleRepository;

	// 자료구조 :: 보드 => BoardController, Board ,,,,
	List<Class<?>> cardApiCaller = List.of(
		CardController.class,
		CardService.class,
		CardMatcherService.class
	);

	// 자료구조 :: 카드
	List<Class<?>> boardApiCaller = List.of(
		BoardController.class,
		BoardService.class
	);


	public Role getRole(Long boardId, Long userId) {
		BoardUserRole boardUserRole = boardUserRoleRepository.findByBoardIdAndUserId(boardId, userId)
			.orElseThrow(() -> new IllegalArgumentException("보드에 권한이 없는 유저입니다."));
		return boardUserRole.getRole();
	}

	/*
	 * Board : 총관리자
	 * <p>
	 * <p>
	 * Card : 총관리자 or 매니저
	 * <p>
	 * <p>
	 * Checklist,Issue, IssueComment : 총관리자 or 매니저 or 워커
	 */
	public void validateRole(Class<?> controllerClass, Long userId, Long boardId) { // 보드에 대한 정보를 입력
		// 보드에 따른 사용자의 권한을 가져온다.
		BoardUserRole userRole = boardUserRoleRepository
			.findByBoardIdAndUserId(boardId, userId)
			.orElseThrow(() -> new RuntimeException("해당 보드의 권한이 없는 유저입니다."));

		// Check Role of Board API
		if (boardApiCaller.contains(controllerClass)) {
			if (userRole.getRole().equals(Role.GENERAL_MANAGER)) {
				return;
			} else {
				throw new RuntimeException("Board 요청 처리 시, 총 관리자만 접근할 수 있습니다.");
			}
		}
		// Check Role of Card API
		if (cardApiCaller.contains(controllerClass)) {
			if (userRole.getRole().equals(Role.GENERAL_MANAGER) || userRole.getRole().equals(Role.TEAM_MANAGER)) {
				return;
			} else {
				throw new RuntimeException("Card 요청 처리 시, 총 관리자 혹은 팀 매니저만 접근할 수 있습니다.");
			}
		}
		throw new RuntimeException("요청 처리에 대한 필요한 권한이 없습니다.");
	}
}
