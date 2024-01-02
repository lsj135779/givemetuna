package com.sparta.givemetuna.domain.issue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import com.sparta.givemetuna.global.config.JpaAuditingConfig;
import com.sparta.givemetuna.global.config.QueryDslConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({QueryDslConfig.class, JpaAuditingConfig.class})
class IssueRepositoryTest {

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		// GIVEN
		User user = userRepository.save(User.builder().build());
		Card card = cardRepository.save(Card.builder().title("요청업무#1").cardPriority(CardPriority.HIGH).build());
		List<Issue> issues = new ArrayList<>();
		for (long l = 1L; l < 10L; l++) {
			Issue issue = Issue.builder()
				.title(String.format("도메인이슈 #%d", l))
				.contents(String.format("요청 업무 프로세스 이슈 #%d 에 관하여 여쭤보고 싶습니다.", l))
				.user(user)
				.card(card)
				.issueStatus(IssueStatus.OPEN)
				.build();
			issueRepository.save(issue);
		}
		for (long l = 1L; l < 10L; l++) {
			Issue issue = Issue.builder()
				.title(String.format("도메인이슈 #%d [종료]", l))
				.contents(String.format("요청 업무 프로세스 이슈 #%d 를 잘 이해하지 못 하겠습니다.", l))
				.user(user)
				.card(card)
				.issueStatus(IssueStatus.CLOSED)
				.build();
			issueRepository.save(issue);
		}
	}

	@AfterEach
	void tearDown() {
		issueRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();
	}

	@Test
	@DisplayName("회원에 대한 원하는 이슈를 단건 조회합니다.")
	public void 회원_이슈_단건조회() {
		// GIVEN
		User user = userRepository.save(User.builder().build());
		Issue issue = Issue.builder()
			.title("도메인이슈 : 업무 프로세스가 이상합니다")
			.contents("요청주신 업무 관련하여 프로세스에 대해 이해가 되지 않습니다.")
			.user(user)
			.issueStatus(IssueStatus.OPEN)
			.build();
		issueRepository.save(issue);

		// WHEN
		Issue foundIssue = issueRepository.findByIdAndUser(issue.getId(), user)
			.orElseThrow(() -> new RuntimeException("원하시는 Issue는 존재하지 않습니다."));

		// THEN
		assertEquals(issue.getId(), foundIssue.getId());
		assertEquals(user.getId(), foundIssue.getUser().getId());
	}

	@Test
	@DisplayName("회원에 대한 이슈리스트를 조건 조회합니다.")
	public void 회원_이슈_리스트_조건조회() {
		// WHEN
		IssueSelectCondition condition = IssueSelectCondition.builder()
			.issueStatus(IssueStatus.CLOSED)
			.build();
		List<IssueReadResponseDto> selectedReadResponseDtos = issueRepository.selectByCondition(condition);

		// THEN
		List<IssueReadResponseDto> filteredReadResponseDtos = issueRepository.findAll().stream()
			.filter(issue -> issue.getIssueStatus().equals(IssueStatus.CLOSED))
			.map(IssueReadResponseDto::of)
			.toList();
		assertEquals(selectedReadResponseDtos, filteredReadResponseDtos);
		assertEquals(9L, selectedReadResponseDtos.size());
	}

	@Test
	@DisplayName("회원에 대한 이슈 리스트를 조건조회하여 페이징 처리합니다.")
	public void 회원_이슈_리스트_조건조회_페이징() {
		// WHEN
		Pageable pageable = PageRequest.of(
			0,
			1,
			Sort.by("title").descending()
				.and(Sort.by("id").ascending())
		);
		IssueSelectCondition condition = IssueSelectCondition.builder()
			.issueStatus(IssueStatus.OPEN)
			.build();
		Page<IssueReadResponseDto> issueReadResponseDtos = issueRepository.selectByConditionPaging(condition, pageable);

		// THEN
		for (IssueReadResponseDto dto : issueReadResponseDtos) {
			System.out.println("dto.toString() = " + dto.toString());
		}
	}
}