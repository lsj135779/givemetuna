package com.sparta.givemetuna.domain.checklist.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.checklist.exception.SelectChecklistNotFoundException;
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

	User user;

	Card card;

	Checklist checklist1;

	Checklist checklist2;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private ChecklistRepository checklistRepository;

	@BeforeEach
	void setUp() {
		user = userRepository.save(User.builder()
			.build());
		card = cardRepository.save(Card.builder()
			.title("title")
			.cardPriority(CardPriority.HIGH)
			.build());

		checklist1 = Checklist.builder()
			.contents("체크리스트#1")
			.user(user) //asignee
			.card(card)
			.build();
		checklist2 = Checklist.builder()
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
		long userId = user.getId();

		// WHEN
		List<Checklist> checklists = checklistRepository.findByUserId(userId);

		// THEN
		boolean hasChecklist1 = checklists.stream().anyMatch(
			checklist -> checklist.getContents().equals(checklist1.getContents()));
		boolean hasChecklist2 = checklists.stream().anyMatch(
			checklist -> checklist.getContents().equals(checklist2.getContents()));
		assertTrue(hasChecklist1);
		assertTrue(hasChecklist2);
	}

	@Test
	@DisplayName("할당자에 따라 체크리스트 옵셔널값을 조회합니다.")
	public void 할당자에따른_체크리스트옵셔널_조회() {
		// GIVEN
		long userId = user.getId();

		// WHEN
		Checklist checklist = checklistRepository.findFirstByAssignee(userId).orElseThrow(SelectChecklistNotFoundException::new);

		// THEN
		assertEquals(checklist1.getContents(), checklist.getContents());
	}
}