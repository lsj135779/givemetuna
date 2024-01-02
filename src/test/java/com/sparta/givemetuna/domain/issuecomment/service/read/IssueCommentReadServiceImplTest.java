package com.sparta.givemetuna.domain.issuecomment.service.read;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import com.sparta.givemetuna.domain.issuecomment.repository.IssueCommentRepository;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class IssueCommentReadServiceImplTest extends IntegrationTestSupport {

	@Autowired
	private IssueCommentReadServiceImpl issueCommentReadServiceImpl;

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
		// GIVEN
		savedUser = userRepository.save(User.builder().build());
		Card card = cardRepository.save(Card.builder().title("요청업무#1").cardPriority(CardPriority.HIGH).build());
		Issue issue = Issue.builder()
			.title(String.format("도메인이슈 #%d", 1))
			.contents(String.format("요청 업무 프로세스 이슈 #%d 에 관하여 여쭤보고 싶습니다.", 1))
			.user(savedUser)
			.card(card)
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
		IssueComment foundComment = issueCommentReadServiceImpl.selectBy(savedComment.getId(), savedIssue, savedUser);

		// THEN
		assertEquals(savedComment.getId(), foundComment.getId());
	}

	@Test
	@DisplayName("IssueComment의 Id값,Issue와 User에 관련된 댓글 조회 불가 시, 예외를 발생합니다.")
	public void commentId_Issue_User관련댓글_조회_예외케이스() {
		// GIVEN
		IssueComment issueComment = IssueComment.addCommentOf(
			"Issue#1에 대하여 코멘트 남깁니다!",
			savedUser,
			savedIssue);
		IssueComment savedComment = issueCommentRepository.save(issueComment);

		// WHEN
		assertThrows(RuntimeException.class, () ->
			issueCommentReadServiceImpl.selectBy(-1, savedIssue, savedUser)
		);
	}

	@Test
	@DisplayName("Issue와 User에 관련된 댓글 리스트를 가져옵니다.")
	public void Issue_User관련댓글_조회() {
		// GIVEN
		List<IssueComment> givenIssueComments = new ArrayList<>();
		for (long l = 0; l < 10; l++) {
			IssueComment issueComment = IssueComment.addCommentOf(
				String.format("Issue#%d에 대하여 코멘트 남깁니다!", l),
				savedUser,
				savedIssue);
			givenIssueComments.add(issueComment);
		}
		issueCommentRepository.saveAll(givenIssueComments);

		// WHEN
		List<IssueComment> issueComments = issueCommentReadServiceImpl.selectBy(savedIssue, savedUser);

		// THEN
		assertEquals(givenIssueComments, issueComments);
	}
}