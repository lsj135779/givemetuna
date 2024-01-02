package com.sparta.givemetuna.domain.issuecomment.service.cud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.card.repository.CardRepository;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issue.entity.IssueStatus;
import com.sparta.givemetuna.domain.issue.repository.IssueRepository;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentDeleteResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentUpdateRequestDto;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentUpdateResponseDto;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
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

	@Test
	@DisplayName("주어진 댓글에 대한 내용을 수정합니다.")
	public void 댓글내용수정() {
		// GIVEN
		IssueComment issueComment = IssueComment.builder()
			.user(savedUser)
			.issue(savedIssue)
			.contents("이슈#29의 도메인 이해가 되지 않는다고요??")
			.build();
		issueCommentRepository.save(issueComment);
		IssueCommentUpdateRequestDto requestDto = IssueCommentUpdateRequestDto.builder()
			.contents("이슈#29의 도메인 이해가 되지 않는다고요?? 이 링크를 다시 한 번 확인해보세요")
			.build();

		// WHEN
		IssueCommentUpdateResponseDto responseDto = issueCommentCudService.updateIssueComment(issueComment, requestDto);

		// THEN
		assertEquals(issueComment.getUser().getId(), responseDto.getUserId());
		assertEquals(issueComment.getIssue().getId(), responseDto.getIssueId());
		assertEquals(issueComment.getContents(), responseDto.getContents());
	}

	@Test
	@DisplayName("이슈에 대한 이슈 댓글을 삭제합니다.")
	public void 이슈댓글삭제() {
		// GIVEN
		IssueComment issueComment = IssueComment.builder()
			.user(savedUser)
			.issue(savedIssue)
			.contents("이슈#29의 도메인 이해가 되지 않는다고요??")
			.build();
		issueCommentRepository.save(issueComment);

		// WHEN
		IssueCommentDeleteResponseDto responseDto = issueCommentCudService.deleteIssueComment(issueComment);

		// THEN
		assertFalse(issueCommentRepository.findById(issueComment.getId()).isPresent());
		assertEquals(issueComment.getId(), responseDto.getIssueCommentId());
		assertFalse(issueComment.getIssue().getIssueComments().contains(issueComment));
	}
}