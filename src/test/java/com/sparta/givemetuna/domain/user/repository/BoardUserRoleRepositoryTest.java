package com.sparta.givemetuna.domain.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.repository.BoardRepository;
import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.global.config.JpaAuditingConfig;
import com.sparta.givemetuna.global.config.QueryDslConfig;
import com.sparta.givemetuna.global.validator.BoardUserRoleValidator;
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
class BoardUserRoleRepositoryTest {

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


	@Test
	@DisplayName("userId와 boardId를 통해 권한을 조회합니다.")
	public void userId_boardId_권한조회() {
		// WHEN
		BoardUserRole gmRole = boardUserRoleRepository.findByBoardIdAndUserId(board.getId(), gm.getId())
			.orElseThrow(() -> new RuntimeException("권한이 없습니다."));
		BoardUserRole tmRole = boardUserRoleRepository.findByBoardIdAndUserId(board.getId(), tm.getId())
			.orElseThrow(() -> new RuntimeException("권한이 없습니다."));
		BoardUserRole workerRole = boardUserRoleRepository.findByBoardIdAndUserId(board.getId(), worker.getId())
			.orElseThrow(() -> new RuntimeException("권한이 없습니다."));

		// THEN
		assertEquals(Role.GENERAL_MANAGER, gmRole.getRole());
		assertEquals(Role.TEAM_MANAGER, tmRole.getRole());
		assertEquals(Role.WORKER, workerRole.getRole());
	}
}