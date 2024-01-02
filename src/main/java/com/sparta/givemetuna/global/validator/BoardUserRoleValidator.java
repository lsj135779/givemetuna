package com.sparta.givemetuna.global.validator;

import com.sparta.givemetuna.domain.board.controller.BoardController;
import com.sparta.givemetuna.domain.board.exception.BoardInvalidAuthorizationException;
import com.sparta.givemetuna.domain.board.service.BoardService;
import com.sparta.givemetuna.domain.card.controller.CardController;
import com.sparta.givemetuna.domain.card.exception.CardInvalidAuthorizationException;
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

	/**
	 * @param apiClass api 호출 클래스 정보
	 * @param userId   회원ID
	 * @param boardId  보드ID
	 * @apiNote depends {@link #getRole(Long, Long)}
	 * @implNote 각 API에 대한 권한은 다음과 같습니다. Board : 총관리자 Card : 총관리자 or 매니저 Checklist,Issue, IssueComment : 총관리자 or 매니저 or 워커
	 * @author @임지훈
	 */
	public void validateRole(Class<?> apiClass, Long userId, Long boardId) { // 보드에 대한 정보를 입력
		// 보드에 따른 사용자의 권한을 가져온다.
		Role role = getRole(boardId, userId);

		// Check Role of Board API
		if (boardApiCaller.contains(apiClass)) {
			if (role.equals(Role.GENERAL_MANAGER)) {
				return;
			} else {
				throw new BoardInvalidAuthorizationException();
			}
		}
		// Check Role of Card API
		if (cardApiCaller.contains(apiClass)) {
			if (role.equals(Role.GENERAL_MANAGER) || role.equals(Role.TEAM_MANAGER)) {
				return;
			} else {
				throw new CardInvalidAuthorizationException();
			}
		}
	}
}
