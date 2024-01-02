package com.sparta.givemetuna.global.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sparta.givemetuna.domain.board.controller.BoardController;
import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.repository.BoardRepository;
import com.sparta.givemetuna.domain.card.controller.CardController;
import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.BoardUserRoleRepository;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import com.sparta.givemetuna.global.config.JpaAuditingConfig;
import com.sparta.givemetuna.global.config.QueryDslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({QueryDslConfig.class, JpaAuditingConfig.class, BoardUserRoleValidator.class})
class BoardUserRoleValidatorTest {

	@Autowired
	private BoardUserRoleValidator boardUserRoleValidator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardUserRoleRepository boardUserRoleRepository;

	private User gm;

	private User tm;

	private User worker;

	private Board board;

	@BeforeEach
	void setUp() {
		gm = userRepository.save(
			User.builder()
				.build()
		);
		tm = userRepository.save(
			User.builder()
				.build()
		);
		worker = userRepository.save(
			User.builder()
				.build()
		);
		board = boardRepository.save(
			Board.builder()
				.user(gm)
				.build()
		);
		boardUserRoleRepository.save(new BoardUserRole(gm, Role.GENERAL_MANAGER, board));
		boardUserRoleRepository.save(new BoardUserRole(tm, Role.TEAM_MANAGER, board));
		boardUserRoleRepository.save(new BoardUserRole(worker, Role.WORKER, board));
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
	@Test
	@DisplayName("카드에 대한 API 수행 시, 총관리자 or 매니저 이여야합니다.")
	public void 카드요청_권한체킹() {
		// WHEN
		// THEN
		boardUserRoleValidator.validateRole(CardController.class, gm.getId(), board.getId());
		boardUserRoleValidator.validateRole(CardController.class, tm.getId(), board.getId());
	}

	@Test
	@DisplayName("카드에 대한 API 수행 시, 총관리자 or 매니저가 아닌 경우 예외처리를 합니다.")
	public void 카드요청_권한체킹_예외언해피케이스() {
		// WHEN
		// THEN
		assertThrows(RuntimeException.class,
			() -> boardUserRoleValidator.validateRole(CardController.class, worker.getId(), board.getId()));
	}

	@Test
	@DisplayName("보드에 대한 API 수행 시, 총관리자 이여야합니다.")
	public void 보드요청_권한체킹() {
		// WHEN
		// THEN
		boardUserRoleValidator.validateRole(BoardController.class, gm.getId(), board.getId());
	}

	@Test
	@DisplayName("보드에 대한 API 수행 시, 총관리자가 아닌 경우 예외처리를 합니다.")
	public void 보드요청_권한체킹_예외언해피케이스() {
		// WHEN
		// THEN
		assertThrows(RuntimeException.class,
			() -> boardUserRoleValidator.validateRole(BoardController.class, worker.getId(), board.getId()));
	}
}