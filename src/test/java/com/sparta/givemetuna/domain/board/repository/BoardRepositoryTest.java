package com.sparta.givemetuna.domain.board.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.BoardUserRoleRepository;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import com.sparta.givemetuna.global.config.JpaAuditingConfig;
import com.sparta.givemetuna.global.config.QueryDslConfig;
import java.util.HashMap;
import java.util.Map;
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
@Import({QueryDslConfig.class, JpaAuditingConfig.class})
class BoardRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardUserRoleRepository boardUserRoleRepository;

	@BeforeEach
	void setUp() {
		User user = userRepository.save(User.builder().build());
	}

	@Test
	@DisplayName("회원에 대한 권한 설정을 합니다.")
	public void 회원권한설정() {
		// GIVEN
		Board board1 = boardRepository.save(
			Board.builder()
				.id(1L)
				.build());
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
		board1.addUsersWithRole(users);

		// THEN
		boolean matchWithSoo = board1.getInvitedUserRole().stream()
			.filter(boardUserRole -> boardUserRole.getUser().equals(soo123))
			.filter(boardUserRole -> boardUserRole.getRole().equals(Role.TEAM_MANAGER))
			.anyMatch(boardUserRole -> boardUserRole.getBoard().equals(board1));
		boolean matchWithJihoon = board1.getInvitedUserRole().stream()
			.filter(boardUserRole -> boardUserRole.getUser().equals(jihoon123))
			.filter(boardUserRole -> boardUserRole.getRole().equals(Role.WORKER))
			.anyMatch(boardUserRole -> boardUserRole.getBoard().equals(board1));
		assertTrue(matchWithSoo);
		assertTrue(matchWithJihoon);
	}
}