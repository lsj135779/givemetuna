package com.sparta.givemetuna.domain.checklist.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import com.sparta.givemetuna.global.config.JpaAuditingConfig;
import com.sparta.givemetuna.global.config.QueryDslConfig;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({QueryDslConfig.class, JpaAuditingConfig.class})
class ChecklistRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private ChecklistRepository checklistRepository;

	@BeforeEach
	void setUp() {
		User user = userRepository.save(User.builder().id(1L)
			.build());
		Card card = cardRepository.save(Card.builder()
			.title("title")
			.cardPriority(CardPriority.HIGH)
			.build());

		Checklist checklist1 = Checklist.builder()
			.contents("체크리스트#1")
			.user(user) //asignee
			.card(card)
			.build();
		Checklist checklist2 = Checklist.builder()
			.contents("체크리스트#2")
			.user(user)
			.card(card)
			.build();
		checklistRepository.save(checklist1);
		checklistRepository.save(checklist2);
	}

	@Test
	@DisplayName("할당자에 따라 체크리스트 리스트값을 조회합니다.")
	public void 할당자에따른_체크리스트리스트_조회() {
		// GIVEN
		long userId = 1L;

		// WHEN
		List<Checklist> checklists = checklistRepository.findByUserId(1L);

		// THEN
		boolean hasChecklist1 = checklists.stream().anyMatch(
			checklist -> checklist.getContents().equals("체크리스트#1"));
		boolean hasChecklist2 = checklists.stream().anyMatch(
			checklist -> checklist.getContents().equals("체크리스트#1"));
		assertTrue(hasChecklist1);
		assertTrue(hasChecklist2);
	}

	@Test
	@DisplayName("할당자에 따라 체크리스트 옵셔널값을 조회합니다.")
	public void 할당자에따른_체크리스트옵셔널_조회() {
		// GIVEN
		long userId = 1L;

		// WHEN
		Checklist checklist = checklistRepository.findFirstByAssignee(1L).get();

		// THEN
		assertEquals("체크리스트#1", checklist.getContents());
	}
}