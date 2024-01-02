package com.sparta.givemetuna.board.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.givemetuna.board.IntegrationTest;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.repository.BoardRepository;
import com.sparta.givemetuna.domain.board.service.BoardService;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.BoardUserRoleRepository;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BoardServiceTest extends IntegrationTest {

	@Autowired
	protected BoardService boardService;

	Board board1;

	Board board2;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardUserRoleRepository boardUserRoleRepository;

	@BeforeEach
	void setUp() {
		User user = userRepository.save(User.builder().build());
		board1 = boardRepository.save(
			Board.builder()
				.id(1L)
				.build());
		board2 = boardRepository.save(
			Board.builder()
				.id(2L)
				.build());
	}

	@AfterEach
	void tearDown() {
		boardUserRoleRepository.deleteAllInBatch();
		boardRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();
	}

	@DisplayName("board 생성 테스트")
	@Test
	void createBoard() {
		// GIVEN
		long boardId = board1.getId();

		// WHEN
		Board board = boardService.getBoard(boardId);

		// THEN
		assertEquals(boardId, board.getId());
	}

	@Test
	@DisplayName("총관리자가 설정한 권한을 가지고 있는 회원을 초대합니다.")
	public void 설정한_권한_회원초대() {
		// GIVEN
		long boardId = board1.getId();
		Map<User, Role> users = new HashMap<>();
		User soo123 = userRepository.save(User.builder()
			.account("soo123")
			.build());
		User jihoon123 = userRepository.save(User.builder()
			.account("jihoon123")
			.build());
		users.put(soo123, Role.TEAM_MANAGER);
		users.put(jihoon123, Role.WORKER);

		// WHEN
		boardService.inviteUser(boardId, users);

		// THEN
		boolean isJihoonWorkerInBoard1 = jihoon123.getBoardUserRoles().stream()
			.filter(boardUserRole -> boardUserRole.getRole().equals(Role.WORKER))
			.anyMatch(boardUserRole -> boardUserRole.getBoard().getId().equals(boardId));
		boolean isSooTMInBoard1 = soo123.getBoardUserRoles().stream()
			.filter(boardUserRole -> boardUserRole.getRole().equals(Role.TEAM_MANAGER))
			.anyMatch(boardUserRole -> boardUserRole.getBoard().getId().equals(boardId));
		assertTrue(isJihoonWorkerInBoard1);
		assertTrue(isSooTMInBoard1);
	}
}
