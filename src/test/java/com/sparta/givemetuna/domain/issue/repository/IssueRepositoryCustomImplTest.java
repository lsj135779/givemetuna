package com.sparta.givemetuna.domain.issue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.querydsl.jpa.impl.JPAQuery;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({QueryDslConfig.class, JpaAuditingConfig.class})
class IssueRepositoryCustomImplTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private IssueRepositoryCustomImpl issueRepositoryCustom;

	@BeforeEach
	void setUp() {
		// GIVEN
		User user = userRepository.save(User.builder().build());
		Card card = cardRepository.save(Card.builder().build());
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
	}

	@Test
	@DisplayName("조건에 따른 조회쿼리에 따른 Issue 리스트를 구합니다.")
	public void 조건조회_쿼리_issue리스트반환() {
		// GIVEN
		IssueSelectCondition condition = IssueSelectCondition.builder()
			.issueStatus(IssueStatus.OPEN)
			.build();

		// WHEN
		JPAQuery<Issue> issuesByCondition = ReflectionTestUtils.invokeMethod(issueRepositoryCustom, "queryAllBy", condition);

		// THEN
		assert issuesByCondition != null;
		assertEquals(9, issuesByCondition.fetch().size());
	}

	@Test
	@DisplayName("조건에 따른 조회쿼리에 따른 IssueReadResponseDto 리스트를 구합니다.")
	public void 조건조회_쿼리_dto리스트반환() {
		// GIVEN
		IssueSelectCondition conditionOfStatus = IssueSelectCondition.builder()
			.issueStatus(IssueStatus.OPEN)
			.build();
		IssueSelectCondition conditionOfStatusAndTitle = IssueSelectCondition.builder()
			.issueStatus(IssueStatus.OPEN)
			.title("도메인이슈 #3")
			.build();

		// WHEN
		List<IssueReadResponseDto> dtosOfStatus = issueRepositoryCustom.selectByCondition(conditionOfStatus);
		List<IssueReadResponseDto> dtosOfStatusAndTitle = issueRepositoryCustom.selectByCondition(conditionOfStatusAndTitle);

		// THEN
		assertEquals(9, dtosOfStatus.size());
		assertEquals(1, dtosOfStatusAndTitle.size());
	}
}