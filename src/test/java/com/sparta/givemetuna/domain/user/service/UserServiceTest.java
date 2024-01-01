package com.sparta.givemetuna.domain.user.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.givemetuna.domain.board.dto.Invitation;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceTest extends IntegrationTestSupport {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@BeforeEach
	void setUp() {
		userRepository.save(
			User.builder()
				.account("jihoon123")
				.build()
		);
		userRepository.save(
			User.builder()
				.account("yoosup123")
				.build()
		);
		userRepository.save(
			User.builder()
				.account("sunghoon123")
				.build()
		);
	}

	@AfterEach
	void tearDown() {
		userRepository.deleteAllInBatch();
	}

	@Test
	@DisplayName("유저 계정이름 리스트를 통해 유저 리스트를 가져옵니다.")
	public void 유저계정리스트로_유저리스트조회() {
		// GIVEN
		List<Invitation> invitations = List.of(
			Invitation.builder()
				.userAccount("yoosup123")
				.role(Role.TEAM_MANAGER)
				.build(),
			Invitation.builder()
				.userAccount("sunghoon123")
				.role(Role.WORKER)
				.build(),
			Invitation.builder()
				.userAccount("jihoon123")
				.role(Role.WORKER)
				.build()
		);

		// WHEN
		Map<User, Role> accountsWithRole = userService.findbyAccountsWithRole(invitations);

		// THEN
		boolean hasYoosupWithTM = accountsWithRole.entrySet().stream()
			.filter(e -> Role.TEAM_MANAGER.equals(e.getValue()))
			.map(Entry::getKey)
			.anyMatch(user -> user.getAccount().equals("yoosup123"));
		boolean hasSunghoonWithWorker = accountsWithRole.entrySet().stream()
			.filter(e -> Role.WORKER.equals(e.getValue()))
			.map(Entry::getKey)
			.anyMatch(user -> user.getAccount().equals("sunghoon123"));
		boolean hasJihoonWithWorker = accountsWithRole.entrySet().stream()
			.filter(e -> Role.WORKER.equals(e.getValue()))
			.map(Entry::getKey)
			.anyMatch(user -> user.getAccount().equals("jihoon123"));
		assertTrue(hasYoosupWithTM);
		assertTrue(hasSunghoonWithWorker);
		assertTrue(hasJihoonWithWorker);
	}

	@Test
	@DisplayName("유저 계정이름 리스트를 통해 유저 리스트를 조회 시, 존재하지 않는 유저인 경우 예외처리합니다.")
	public void 유저계정리스트로_유저리스트조회_예외언해피케이스() {
		// GIVEN
		List<Invitation> invitations = List.of(
			Invitation.builder()
				.userAccount("yyy")
				.role(Role.TEAM_MANAGER)
				.build(),
			Invitation.builder()
				.userAccount("sss")
				.role(Role.WORKER)
				.build(),
			Invitation.builder()
				.userAccount("jj")
				.role(Role.WORKER)
				.build()
		);

		// WHEN
		// THEN
		assertThrows(RuntimeException.class, () -> userService.findbyAccountsWithRole(invitations));
	}
}