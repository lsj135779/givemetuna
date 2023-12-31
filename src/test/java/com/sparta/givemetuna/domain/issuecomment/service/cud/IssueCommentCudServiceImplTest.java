package com.sparta.givemetuna.domain.issuecomment.service.cud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateResponseDto;
import com.sparta.givemetuna.domain.issuecomment.repository.IssueCommentRepository;
import com.sparta.givemetuna.domain.support.IntegrationTestSupport;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class IssueCommentCudServiceImplTest extends IntegrationTestSupport {

	@Autowired
	private IssueCommentCudService issueCommentCudService;

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
		Card card = cardRepository.save(Card.builder().build());
		Issue issue = Issue.builder()
			.user(savedUser)
			.card(card)
			.title("이슈#29")
			.contents("이슈#29의 도메인이 잘 이해가 가지 않네요.궁시렁궁시렁.")
			.issueStatus(IssueStatus.OPEN)
			.build();
		savedIssue = issueRepository.save(issue);
	}

	@AfterEach
	void tearDown() {
		issueCommentRepository.deleteAllInBatch();
		issueRepository.deleteAllInBatch();
		cardRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();
	}

	@Test
	@DisplayName("이슈에 대한 댓글을 생성합니다.")
	public void 이슈댓글_생성() {
		// GIVEN
		IssueCommentCreateRequestDto requestDto = IssueCommentCreateRequestDto.builder()
			.contents("이슈#29에 대해 요청응답 드리겠습니다.샤블라두블라.")
			.build();

		// WHEN
		IssueCommentCreateResponseDto responseDto = issueCommentCudService.createIssueComment(savedUser, savedIssue, requestDto);

		// THEN
		boolean hasIssueComment = savedIssue.getIssueComments().stream()
			.anyMatch(issueComment ->
				issueComment.getContents()
					.contains(responseDto.getContents()));
		assertEquals(savedIssue.getId(), responseDto.getIssueId());
		assertTrue(hasIssueComment);
	}

}