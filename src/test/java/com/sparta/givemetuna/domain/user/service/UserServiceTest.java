package com.sparta.givemetuna.domain.user.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import java.util.List;
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

	@Test
	@DisplayName("유저 계정이름 리스트를 통해 유저 리스트를 가져옵니다.")
	public void 유저계정리스트로_유저리스트조회() {
		// GIVEN
		List<String> accounts = List.of("jihoon123",
			"yoosup123",
			"sunghoon123");

		// WHEN
		List<User> users = userService.findbyAccounts(accounts);

		// THEN
		boolean hasJihoon = users.stream()
			.anyMatch(user -> user.getAccount().equals("jihoon123"));
		boolean hasYoosup = users.stream()
			.anyMatch(user -> user.getAccount().equals("yoosup123"));
		boolean hasSunghoon = users.stream()
			.anyMatch(user -> user.getAccount().equals("sunghoon123"));
		assertTrue(hasJihoon);
		assertTrue(hasYoosup);
		assertTrue(hasSunghoon);
	}

	@Test
	@DisplayName("유저 계정이름 리스트를 통해 유저 리스트를 조회 시, 존재하지 않는 유저인 경우 예외처리합니다.")
	public void 유저계정리스트로_유저리스트조회_예외언해피케이스() {
		// GIVEN

		// WHEN

		// THEN

	}
}