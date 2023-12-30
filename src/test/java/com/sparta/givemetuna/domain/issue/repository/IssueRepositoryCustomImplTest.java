package com.sparta.givemetuna.domain.issue.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.configuration.QueryDslConfig;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.Status;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import com.sparta.givemetuna.global.config.JpaAuditingConfig;
import java.util.ArrayList;
import java.util.List;
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

	@Test
	@DisplayName("조회")
	public void 조회() {
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
				.status(Status.OPEN)
				.build();
			issueRepository.save(issue);
		}

		// WHEN
		IssueSelectCondition condition = IssueSelectCondition.builder()
			.status(Status.CLOSED)
			.build();
		JPAQuery<Issue> getJpaQuery = ReflectionTestUtils.invokeMethod(issueRepositoryCustom, "selectBy", condition);

		// THEN
		List<Issue> dtos = getJpaQuery.fetch();
		dtos.forEach(System.out::println);
	}
}