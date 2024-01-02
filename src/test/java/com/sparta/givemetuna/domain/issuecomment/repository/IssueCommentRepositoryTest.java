package com.sparta.givemetuna.domain.issuecomment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.user.entity.User;
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
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@Import({QueryDslConfig.class, JpaAuditingConfig.class})
class IssueCommentRepositoryTest {

	@Autowired
	private IssueCommentRepository issueCommentRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private UserRepository userRepository;

	private User savedUser;

	private Issue savedIssue;

	@BeforeEach
	void setUp() {
		savedUser = userRepository.save(User.builder().build());
		Card card = cardRepository.save(Card.builder().title("요청업무#1").cardPriority(CardPriority.HIGH).build());
		Issue issue = Issue.builder()
			.user(savedUser)
			.card(card)
			.title("이슈#29")
			.contents("이슈#29의 도메인이 잘 이해가 가지 않네요.궁시렁궁시렁.")
			.issueStatus(IssueStatus.OPEN)
			.build();
		savedIssue = issueRepository.save(issue);
	}

	@Test
	@DisplayName("IssueComment의 Id값,Issue와 User에 관련된 댓글을 가져옵니다.")
	public void commentId_Issue_User관련댓글_조회() {
		// GIVEN
		IssueComment issueComment = IssueComment.addCommentOf(
			"Issue#1에 대하여 코멘트 남깁니다!",
			savedUser,
			savedIssue);
		IssueComment savedComment = issueCommentRepository.save(issueComment);

		// WHEN
		IssueComment foundComment = issueCommentRepository.findByIdAndIssueAndUser(
				savedComment.getId(),
				savedIssue, savedUser
			)
			.orElseThrow(() -> new RuntimeException("관련 코멘트가 존재하지 않습니다."));

		// THEN
		assertEquals(savedComment, foundComment);
	}
}